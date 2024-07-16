package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers.ScoreboardData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class SimpleScoreboard extends AbstractScoreboard {

    private final HashMap<OfflinePlayer, Scoreboard> scoreboards = new HashMap<>();
    private final HashMap<OfflinePlayer, HashMap<String, Integer>> timesUsedInEntries = new HashMap<>();

    public SimpleScoreboard(ScoreboardData data, Collection<? extends OfflinePlayer> receivers, GeneralElement<Long, ?> timeToLive) {
        super(data, new HashSet<>(receivers), timeToLive, UpdateMode.PER_PLAYER);
    }

    @Override
    protected void show(Player p) {
        scoreboards.put(p, getNewBoard(p));
        initTitle(p);
        initLines(p, 0);
        p.setScoreboard(scoreboards.get(p));
    }

    @Override
    protected void hide(Player p) {
        p.setScoreboard(removeSidebar(p));
    }

    @Override
    protected void perPlayerUpdate(Player p) {
        initTitle(p);
        for (int i = 0; i < data.getLines().size(); i++) {
            updateLine(p, i);
        }
    }

    @Override
    protected void globalUpdate() {

    }



    @Override
    protected void addLineInternal(int index) {
        for (Player p : getCurrentlyViewing()) {
            resetScoreboard(p, data.getLines().size() - 1);
        }

        if (isAnyoneWatching())
            update();
    }

    @Override
    protected void removeLineInternal(int index) {
        for (Player p : getCurrentlyViewing()) {
            resetScoreboard(p, data.getLines().size() + 1);
        }

        if (isAnyoneWatching())
            update();
    }

    @Override
    protected void updateLines(int oldSize) {
        for (Player p : getCurrentlyViewing()) {
            resetScoreboard(p, oldSize);
        }
    }

    private void updateLine(Player player, int index) {
        Scoreboard scoreboard = scoreboards.get(player);
        Component text = data.getLines().get(index).getFromContext(OfflinePlayer.class, player);
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

    private void initTitle(Player player) {
        Objective obj = scoreboards.get(player).getObjective("[CrispyCommons]");
        Component text = data.getTitle().getFromContext(OfflinePlayer.class, player);
        obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(text)));
    }

    private void initLines(Player player, int startIndex) {
        Scoreboard scoreboard = scoreboards.get(player);
        for (int i = startIndex; i < data.getLines().size(); i++) {
            Objective obj = scoreboard.getObjective("[CrispyCommons]");

            Component text = data.getLines().get(i).getFromContext(OfflinePlayer.class, player);
            String line = ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(text));
            String teamEntry = getEntry(player, line, scoreboard);
            Team team = scoreboard.registerNewTeam(String.valueOf(i));
            team.addEntry(teamEntry);
            team.setPrefix(getPrefix(line));
            team.setSuffix(getSuffix(line, teamEntry));
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

    private String getPrefix(String line) {
        if (line.length() <= 16) {
            return line;
        }
        if (line.charAt(15) == ChatColor.COLOR_CHAR) {
            return line.substring(0, 15);
        }
        return line.substring(0, 16);
    }

    private String getEntry(Player player, String line, Scoreboard bukkitScoreboard) {
        if (line.length() < 16) {
            return getUniqueEntry(player, "", bukkitScoreboard);
        }
        if (line.charAt(15) == ChatColor.COLOR_CHAR) {
            return getUniqueEntry(player, line.substring(15, 17), bukkitScoreboard);
        }
        return getUniqueEntry(player, ChatColor.getLastColors(line.substring(0, 16)), bukkitScoreboard);
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
        }  else {
            String finalString = line.substring(16);
            finalString = lastPrefixColor + finalString;
            return finalString.substring(0, Math.min(finalString.length(), 16));
        }
    }

    private String getUniqueEntry(Player player, String suffix, Scoreboard bukkitScoreboard) {
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
        if(lastColors.isEmpty()) {
            return "";
        }
        int lastReset = lastColors.lastIndexOf(ChatColor.translateAlternateColorCodes('&', "&r"));
        lastReset = lastReset == -1 ? 0 : lastReset + 2;

        return lastColors.substring(lastReset);
    }

    private void resetScoreboard(Player p, int teamSize) {
        timesUsedInEntries.put(p, new HashMap<>());
        p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
        p.getScoreboard().getObjective("[CrispyCommons]").unregister();
        p.setScoreboard(getNewBoard(p));
        removeLines(p, 0, teamSize);
        initLines(p, 0);
    }

    private Scoreboard getNewBoard(Player p) {
        Scoreboard board = p.getScoreboard();
        board.registerNewObjective("[CrispyCommons]", "dummy");
        board.getObjective("[CrispyCommons]").setDisplaySlot(DisplaySlot.SIDEBAR);
        return board;
    }

    private Scoreboard removeSidebar(Player p) {
        Scoreboard board = p.getScoreboard();
        board.getObjective("[CrispyCommons]").unregister();
        return board;
    }
}
