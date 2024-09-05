package dev.acrispycookie.crispycommons.v1_20_R3.implementations.visual.scoreboard;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import dev.acrispycookie.crispycommons.implementations.visual.scoreboard.SimpleScoreboard;
import dev.acrispycookie.crispycommons.implementations.visual.scoreboard.data.ScoreboardData;
import dev.acrispycookie.crispycommons.utility.logging.CrispyLogger;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.minecraft.EnumChatFormat;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.chat.numbers.FixedFormat;
import net.minecraft.network.protocol.game.*;
import net.minecraft.world.scores.ScoreboardObjective;
import net.minecraft.world.scores.criteria.IScoreboardCriteria;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class SimpleScoreboard_1_20_R3 extends SimpleScoreboard {

    /**
     * A map that associates each player with their respective scoreboard ID.
     */
    private final HashMap<OfflinePlayer, String> scoreboards = new HashMap<>();

    public SimpleScoreboard_1_20_R3(ScoreboardData data, Collection<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, receivers, timeToLive, isPublic);
    }

    @Override
    protected void show(@NotNull Player player) {
        Component titleText = getTitle().getFromContext(OfflinePlayer.class, player);
        List<Component> lines = getLines().stream().map(e -> e.getFromContext(OfflinePlayer.class, player)).collect(Collectors.toList());

        init(player);
        sendObjective(player, titleText, ObjectiveAction.UPDATE);
        for (int i = 0; i < lines.size(); i++) {
            sendTeam(player, i, lines.get(i), TeamAction.ADD);
        }
    }

    @Override
    protected void hide(@NotNull Player player) {
        for (int i = 0; i < getLines().size(); i++) {
            sendTeam(player, i, Component.empty(), TeamAction.REMOVE);
        }
        sendObjective(player, Component.empty(), ObjectiveAction.REMOVE);
    }

    @Override
    protected void perPlayerUpdate(@NotNull Player player) {

    }

    @Override
    protected void globalUpdate() {
        updateTitle();
        for (int i = 0; i < getLines().size(); i++) {
            updateLine(i);
        }
    }

    @Override
    protected void addLineInternal(int index) {
        for (Player p : getCurrentlyViewing()) {
            List<Component> lines = getLines().stream().map(e -> e.getFromContext(OfflinePlayer.class, p)).collect(Collectors.toList());
            resetScoreboard(p, getLines().size() - 1, lines);
        }
    }

    @Override
    protected void removeLineInternal(int index) {
        for (Player p : getCurrentlyViewing()) {
            List<Component> lines = getLines().stream().map(e -> e.getFromContext(OfflinePlayer.class, p)).collect(Collectors.toList());
            resetScoreboard(p, getLines().size() + 1, lines);
        }
    }

    @Override
    public void updateTitle() {
        for (Player p : getCurrentlyViewing()) {
            Component titleText = getTitle().getFromContext(OfflinePlayer.class, p);
            sendObjective(p, titleText, ObjectiveAction.UPDATE);
        }
    }

    @Override
    public void updateLine(int index) {
        for (Player p : getCurrentlyViewing()) {
            List<Component> lines = getLines().stream().map(e -> e.getFromContext(OfflinePlayer.class, p)).collect(Collectors.toList());
            sendScore(p, index, lines.get(index), Component.empty(), ScoreAction.CHANGE);
        }
    }

    @Override
    protected void updateLines(int oldSize) {
        for (Player p : getCurrentlyViewing()) {
            List<Component> lines = getLines().stream().map(e -> e.getFromContext(OfflinePlayer.class, p)).collect(Collectors.toList());
            resetScoreboard(p, oldSize, lines);
        }
    }

    private void resetScoreboard(Player p, int oldSize, List<Component> lines) {
        int newSize = lines.size();

        if (newSize > oldSize) {
            for (int i = oldSize; i < newSize; i++) {
                sendTeam(p, i, Component.empty(), TeamAction.ADD);
                sendScore(p, i, Component.empty(), Component.empty(), ScoreAction.CHANGE);
            }
        } else if (newSize < oldSize) {
            for (int i = oldSize; i > newSize; i--) {
                sendTeam(p, i - 1, Component.empty(), TeamAction.REMOVE);
                sendScore(p, i - 1, Component.empty(), Component.empty(), ScoreAction.REMOVE);
            }
        }

        for (int i = 0; i < lines.size(); i++) {
            //sendTeam(p, i, lines.get(i), TeamAction.UPDATE);
            sendScore(p, i, lines.get(i), Component.empty(), ScoreAction.CHANGE);
        }
    }

    private void init(Player p) {
        String id = "crispycommons-" + Integer.toHexString(ThreadLocalRandom.current().nextInt());
        scoreboards.put(p, id);
        ScoreboardObjective objective = getObjectiveOptions(id, "");
        PacketPlayOutScoreboardObjective createObj = new PacketPlayOutScoreboardObjective(objective, 0); // create
        ((CraftPlayer) p).getHandle().c.b(createObj);
        PacketPlayOutScoreboardDisplayObjective displayObj = new PacketPlayOutScoreboardDisplayObjective(net.minecraft.world.scores.DisplaySlot.b, objective);
        ((CraftPlayer) p).getHandle().c.b(displayObj);
    }

    private void sendObjective(Player p, Component text, ObjectiveAction action) {
        String id = scoreboards.get(p);
        ScoreboardObjective objective = getObjectiveOptions(id, LegacyComponentSerializer.legacySection().serialize(text));
        PacketPlayOutScoreboardObjective updateObj = new PacketPlayOutScoreboardObjective(objective, action.ordinal()); //update
        ((CraftPlayer) p).getHandle().c.b(updateObj);
    }

    private void sendTeam(Player p, int index, Component text, TeamAction action) {
        try {
            String line = LegacyComponentSerializer.legacySection().serialize(text);
            String teamName = scoreboards.get(p) + "-" + index;
            PacketPlayOutScoreboardTeam.b options = getTeamOptions(getPrefix(line), getSuffix(line), index);

            Constructor<?> constructor = PacketPlayOutScoreboardTeam.class.getDeclaredConstructor(String.class, int.class, Optional.class, Collection.class);
            constructor.setAccessible(true);
            PacketPlayOutScoreboardTeam createTeam =
                    (PacketPlayOutScoreboardTeam) constructor.newInstance(teamName, action.ordinal(), Optional.of(options),
                            action == TeamAction.ADD ? Collections.singletonList(ChatColor.values()[index].toString()) : new ArrayList<>());
            ((CraftPlayer) p).getHandle().c.b(createTeam);
        } catch (InstantiationException | NoSuchFieldException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            CrispyLogger.printException(CrispyCommons.getPlugin(), e, "Couldn't init scoreboard lines. Something went wrong!");
        }
    }

    private void sendScore(Player p, int index, Component text, Component format, ScoreAction action) {
        String textString = LegacyComponentSerializer.legacySection().serialize(text);
        String formatString = LegacyComponentSerializer.legacySection().serialize(format);
        String objName = scoreboards.get(p);
        String teamName = objName + "-" + index;

        if (action == ScoreAction.REMOVE) {
            ClientboundResetScorePacket resetScore = new ClientboundResetScorePacket(teamName, objName);
            ((CraftPlayer) p).getHandle().c.b(resetScore);
        } else {
            PacketPlayOutScoreboardScore setScore = new PacketPlayOutScoreboardScore(teamName, objName, 15 - index, IChatBaseComponent.b(textString), new FixedFormat(IChatBaseComponent.b(formatString)));
            ((CraftPlayer) p).getHandle().c.b(setScore);
        }
    }

    private ScoreboardObjective getObjectiveOptions(String name, String title) {
        return new ScoreboardObjective(null,
                name, // name
                null, // criteria
                IChatBaseComponent.b(title), //title
                IScoreboardCriteria.EnumScoreboardHealthDisplay.a, // number display
                true, //display auto update
                null);
    }

    private PacketPlayOutScoreboardTeam.b getTeamOptions(String prefix, String suffix, int index) throws InstantiationException, NoSuchFieldException, IllegalAccessException {
        PacketPlayOutScoreboardTeam.b options = (PacketPlayOutScoreboardTeam.b) getUnsafe().allocateInstance(PacketPlayOutScoreboardTeam.b.class);
        Field displayName = options.getClass().getDeclaredField("a");
        Field prefixField = options.getClass().getDeclaredField("b");
        Field suffixField = options.getClass().getDeclaredField("c");
        Field nameTagVisibility = options.getClass().getDeclaredField("d");
        Field collisionRule = options.getClass().getDeclaredField("e");
        Field color = options.getClass().getDeclaredField("f");
        displayName.setAccessible(true);
        displayName.set(options, IChatBaseComponent.b(""));
        prefixField.setAccessible(true);
        prefixField.set(options, IChatBaseComponent.b(prefix));
        suffixField.setAccessible(true);
        suffixField.set(options, IChatBaseComponent.b(suffix));
        nameTagVisibility.setAccessible(true);
        nameTagVisibility.set(options, "always");
        collisionRule.setAccessible(true);
        collisionRule.set(options, "always");
        color.setAccessible(true);
        color.set(options, EnumChatFormat.values()[index]);

        return options;
    }

    private String getPrefix(String line) {
        if (line.length() <= 64) {
            return line;
        }
        if (line.charAt(63) == ChatColor.COLOR_CHAR) {
            return line.substring(0, 63);
        }
        return line.substring(0, 64);
    }

    private String getSuffix(String line) {
        if (line.length() <= 64) {
            return "";
        }
        String lastPrefixColor = getStrippedColors(ChatColor.getLastColors(line.substring(0, 64)));
        if (line.charAt(63) == ChatColor.COLOR_CHAR) {
            return line.substring(63);
        } else {
            String finalString = line.substring(64);
            finalString = lastPrefixColor + finalString;
            return finalString.substring(0, Math.min(finalString.length(), 64));
        }
    }

    private String getStrippedColors(String lastColors) {
        if (lastColors.isEmpty()) {
            return "";
        }
        int lastReset = lastColors.lastIndexOf(ChatColor.translateAlternateColorCodes('&', "&r"));
        lastReset = lastReset == -1 ? 0 : lastReset + 2;

        return lastColors.substring(lastReset);
    }

    public Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe)f.get(null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to access Unsafe", e);
        }
    }

    enum TeamAction {
        ADD, REMOVE, UPDATE
    }

    enum ObjectiveAction {
        ADD, REMOVE, UPDATE
    }

    enum ScoreAction {
        CHANGE, REMOVE
    }
}
