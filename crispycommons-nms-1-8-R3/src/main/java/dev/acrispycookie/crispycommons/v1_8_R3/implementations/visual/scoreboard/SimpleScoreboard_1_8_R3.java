package dev.acrispycookie.crispycommons.v1_8_R3.implementations.visual.scoreboard;

import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import dev.acrispycookie.crispycommons.implementations.visual.scoreboard.SimpleScoreboard;
import dev.acrispycookie.crispycommons.implementations.visual.scoreboard.data.ScoreboardData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleScoreboard_1_8_R3 extends SimpleScoreboard {

    /**
     * A map that associates each player with their respective scoreboard.
     */
    private final HashMap<OfflinePlayer, Scoreboard> scoreboards = new HashMap<>();

    /**
     * A map that tracks how many times a specific string has been used as an entry in a player's scoreboard.
     */
    private final HashMap<OfflinePlayer, HashMap<String, Integer>> timesUsedInEntries = new HashMap<>();

    public SimpleScoreboard_1_8_R3(@NotNull ScoreboardData data, @NotNull Collection<? extends OfflinePlayer> receivers, @NotNull TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, receivers, timeToLive, isPublic);
    }

    @Override
    protected void show(@NotNull Player player) {
        Component titleText = getTitle().getFromContext(OfflinePlayer.class, player);
        List<Component> lines = getLines().stream().map(e -> e.getFromContext(OfflinePlayer.class, player)).collect(Collectors.toList());

        scoreboards.put(player, getNewBoard(player));
        initTitle(player, titleText);
        initLines(player, 0, lines);
        player.setScoreboard(scoreboards.get(player));
    }

    @Override
    protected void hide(@NotNull Player player) {
        player.setScoreboard(removeSidebar(player, getLines().size()));
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
            initTitle(p, titleText);
        }
    }

    @Override
    public void updateLine(int index) {
        for (Player p : getCurrentlyViewing()) {
            List<Component> lines = getLines().stream().map(e -> e.getFromContext(OfflinePlayer.class, p)).collect(Collectors.toList());
            updateLine(p, index, lines.get(index));
        }
    }

    @Override
    protected void updateLines(int oldSize) {
        for (Player p : getCurrentlyViewing()) {
            List<Component> lines = getLines().stream().map(e -> e.getFromContext(OfflinePlayer.class, p)).collect(Collectors.toList());
            resetScoreboard(p, oldSize, lines);
        }
    }

    private void updateLine(Player player, int index, Component text) {
        Scoreboard scoreboard = scoreboards.get(player);
        Objective obj = scoreboard.getObjective("[CrispyCommons]");
        Team team = scoreboard.getTeam(String.valueOf(index));

        String line = ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(text));
        line = line.substring(0, Math.min(line.length(), 32));
        String teamEntry = team.getEntries().iterator().next();
        String prefix = getPrefix(line);
        String suffix = getSuffix(line, teamEntry);
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        obj.getScore(teamEntry).setScore(15 - index);
    }

    private void initTitle(Player player, Component text) {
        Objective obj = scoreboards.get(player).getObjective("[CrispyCommons]");
        obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(text)));
    }

    private void initLines(Player player, int startIndex, List<Component> lines) {
        Scoreboard scoreboard = scoreboards.get(player);
        for (int i = startIndex; i < lines.size(); i++) {
            Objective obj = scoreboard.getObjective("[CrispyCommons]");

            Component text = lines.get(i);
            Team team = scoreboard.registerNewTeam(String.valueOf(i));
            String line = ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(text));
            String teamEntry = getEntry(player, line);
            String prefix = getPrefix(line);
            String suffix = getSuffix(line, teamEntry);
            team.addEntry(teamEntry);
            team.setPrefix(prefix);
            team.setSuffix(suffix);
            obj.getScore(teamEntry).setScore(15 - i);
        }
    }

    private void removeLines(Player player, int startIndex, int size) {
        Scoreboard scoreboard = scoreboards.get(player);
        for (int i = startIndex; i < size; i++) {
            Team team = scoreboard.getTeam(String.valueOf(i));
            team.unregister();
        }
    }

    private void resetScoreboard(Player p, int oldTeamSize, List<Component> lines) {
        timesUsedInEntries.put(p, new HashMap<>());
        p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
        p.getScoreboard().getObjective("[CrispyCommons]").unregister();
        p.setScoreboard(getNewBoard(p));
        removeLines(p, 0, oldTeamSize);
        initLines(p, 0, lines);
    }

    private Scoreboard removeSidebar(Player p, int oldTeamSize) {
        Scoreboard board = p.getScoreboard();
        Objective objective = board.getObjective("[CrispyCommons]");
        if (objective != null) {
            objective.unregister();
        }

        for (int i = 0; i < oldTeamSize; i++) {
            Team t = board.getTeam(String.valueOf(i));
            if (t != null) {
                t.unregister();
            }
        }
        return board;
    }

    private Scoreboard getNewBoard(Player p) {
        Scoreboard board = p.getScoreboard();
        board.registerNewObjective("[CrispyCommons]", "dummy");
        board.getObjective("[CrispyCommons]").setDisplaySlot(DisplaySlot.SIDEBAR);
        return board;
    }

    private String getPrefix(String line) {
        if (line.length() <= 16) {
            return line;
        }
        if (line.charAt(15) == ChatColor.COLOR_CHAR) {
            return line.substring(0, 15);
        }
        return line.substring(0, 16);
    }

    private String getEntry(Player player, String line) {
        if (line.length() < 16) {
            return getUniqueEntry(player, "");
        }
        if (line.charAt(15) == ChatColor.COLOR_CHAR) {
            return getUniqueEntry(player, line.substring(15, 17));
        }
        return getUniqueEntry(player, ChatColor.getLastColors(line.substring(0, 16)));
    }

    private String getSuffix(String line, String lastEntry) {
        if (line.length() <= 16) {
            return "";
        }
        String lastEntryColor = getStrippedColors(ChatColor.getLastColors(lastEntry));
        String lastPrefixColor = getStrippedColors(ChatColor.getLastColors(line.substring(0, 16)));
        if (line.charAt(15) == ChatColor.COLOR_CHAR) {
            int startIndex = line.substring(15, 17).equals(lastEntryColor) ? 17 : 15;
            return line.substring(startIndex);
        } else if (lastPrefixColor.equals(lastEntryColor)) {
            return line.substring(16);
        } else {
            String finalString = line.substring(16);
            finalString = lastPrefixColor + finalString;
            return finalString.substring(0, Math.min(finalString.length(), 16));
        }
    }

    private String getUniqueEntry(Player player, String suffix) {
        StringBuilder finalEntry = new StringBuilder(suffix);
        HashMap<String, Integer> timesUsed;
        if (!timesUsedInEntries.containsKey(player)) {
            timesUsed = new HashMap<>();
            timesUsedInEntries.put(player, timesUsed);
        } else {
            timesUsed = timesUsedInEntries.get(player);
        }

        if (!timesUsed.containsKey(suffix)) {
            timesUsed.put(suffix, 1);
            return finalEntry.toString();
        }

        int times = timesUsed.get(suffix);
        for (int i = 0; i < times; i++) {
            finalEntry.insert(0, ChatColor.translateAlternateColorCodes('&', "&r"));
        }
        timesUsed.put(suffix, times + 1);
        timesUsedInEntries.put(player, timesUsed);
        return finalEntry.toString();
    }

    private String getStrippedColors(String lastColors) {
        if (lastColors.isEmpty()) {
            return "";
        }
        int lastReset = lastColors.lastIndexOf(ChatColor.translateAlternateColorCodes('&', "&r"));
        lastReset = lastReset == -1 ? 0 : lastReset + 2;

        return lastColors.substring(lastReset);
    }
}
