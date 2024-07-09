package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers.ScoreboardData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalTextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalTextElement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
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

    public SimpleScoreboard(ScoreboardData data, Collection<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, new HashSet<>(receivers), timeToLive, UpdateMode.PER_PLAYER);
    }

    @Override
    protected void show(Player p) {
        scoreboards.put(p, getNewBoard());
        initTitle(p);
        initLines(p);
        p.setScoreboard(scoreboards.get(p));
    }

    @Override
    protected void hide(Player p) {
        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
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

    private void showLine(Player player, int index) {
        Scoreboard scoreboard = scoreboards.get(player);
        TextElement element = data.getLines().get(index);
        Component text = element instanceof GlobalTextElement ?
                ((GlobalTextElement) element).getRaw() :
                ((PersonalTextElement) element).getRaw(player);
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

    private void hideLine(Player player, int index) {

    }

    private void updateLine(Player player, int index) {
        Scoreboard scoreboard = scoreboards.get(player);
        TextElement element = data.getLines().get(index);
        Component text = element instanceof GlobalTextElement ?
                ((GlobalTextElement) element).getRaw() :
                ((PersonalTextElement) element).getRaw(player);
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
        Component text = data.getTitle() instanceof GlobalTextElement ?
                ((GlobalTextElement) data.getTitle()).getRaw() :
                ((PersonalTextElement) data.getTitle()).getRaw(player);
        obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(text)));
    }

    private void initLines(Player player) {
        Scoreboard scoreboard = scoreboards.get(player);
        for (int i = 0; i < data.getLines().size(); i++) {
            Objective obj = scoreboard.getObjective("[CrispyCommons]");

            TextElement element = data.getLines().get(i);
            Component text = element instanceof GlobalTextElement ?
                    ((GlobalTextElement) element).getRaw() :
                    ((PersonalTextElement) element).getRaw(player);
            String line = ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(text));
            String teamEntry = getEntry(line, scoreboard);
            Team team = scoreboard.registerNewTeam(String.valueOf(i));
            team.addEntry(teamEntry);
            team.setPrefix(getPrefix(line));
            team.setSuffix(getSuffix(line, teamEntry));
            obj.getScore(teamEntry).setScore(15 - i);
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

    private String getEntry(String line, Scoreboard bukkitScoreboard) {
        if (line.length() < 16) {
            return getUniqueEntry(ChatColor.translateAlternateColorCodes('&', "&r"), bukkitScoreboard);
        }
        if (line.charAt(15) == ChatColor.COLOR_CHAR) {
            return getUniqueEntry(line.substring(15, 17), bukkitScoreboard);
        }
        return getUniqueEntry(ChatColor.getLastColors(line.substring(0, 16)), bukkitScoreboard);
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

    private String getUniqueEntry(String suffix, Scoreboard bukkitScoreboard) {
        StringBuilder finalEntry = new StringBuilder(suffix);
        for (Team t : bukkitScoreboard.getTeams()) {
            for (String entry : t.getEntries()) {
                if (finalEntry.toString().endsWith(entry)) {
                    finalEntry.insert(0, ChatColor.translateAlternateColorCodes('&', "&r"));
                }
            }
        }

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

    private Scoreboard getNewBoard() {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        board.registerNewObjective("[CrispyCommons]", "dummy");
        board.getObjective("[CrispyCommons]").setDisplaySlot(DisplaySlot.SIDEBAR);
        return board;
    }
}
