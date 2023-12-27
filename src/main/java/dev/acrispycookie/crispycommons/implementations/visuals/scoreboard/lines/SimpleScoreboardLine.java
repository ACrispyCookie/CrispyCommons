package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.AbstractScoreboardLine;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.TextElement;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Collection;

public class SimpleScoreboardLine extends AbstractScoreboardLine {

    public SimpleScoreboardLine(String staticLine) {
        super(new SimpleTextElement(staticLine));
    }

    public SimpleScoreboardLine(Collection<? extends String> frames, int period) {
        super(null);
        this.element = new TextElement(frames, period, true) {
            @Override
            protected void update() {
                SimpleScoreboardLine.this.update();
            }
        };
    }

    @Override
    protected void initialize() {
        Scoreboard bukkitScoreboard = scoreboard.getBukkitScoreboard();
        Objective obj = bukkitScoreboard.getObjective("[CrispyCommons]");

        String line = ChatColor.translateAlternateColorCodes('&', getCurrentContent());
        String teamEntry = getEntry(line, bukkitScoreboard);
        Team team = bukkitScoreboard.registerNewTeam(String.valueOf(position));
        team.addEntry(teamEntry);
        team.setPrefix(getPrefix(line));
        team.setSuffix(getSuffix(line, teamEntry));
        obj.getScore(teamEntry).setScore(15 - position);
    }

    @Override
    protected void updateInternal() {
        System.out.println("Updating line: " + position);
        Scoreboard bukkitScoreboard = scoreboard.getBukkitScoreboard();
        Objective obj = bukkitScoreboard.getObjective("[CrispyCommons]");
        Team team = bukkitScoreboard.getTeam(String.valueOf(position));

        String line = ChatColor.translateAlternateColorCodes('&', getCurrentContent());
        line = line.substring(0, Math.min(line.length(), 32));
        System.out.println("  Line: " + line);
        String teamEntry = team.getEntries().iterator().next();
        System.out.println("  Entry: " + teamEntry);
        String prefix = getPrefix(line);
        String suffix = getSuffix(line, teamEntry);
        System.out.println("  Prefix: " + prefix);
        System.out.println("  Suffix: " + suffix);
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        obj.getScore(teamEntry).setScore(15 - position);
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
            return getUniqueEntry(line.substring(15, 16), bukkitScoreboard);
        }
        return getUniqueEntry(ChatColor.getLastColors(line.substring(0, 16)), bukkitScoreboard);
    }

    private String getSuffix(String line, String lastEntry) {
        if (line.length() <= 16) {
            return "";
        }
        int prefixEnd = line.charAt(15) == ChatColor.COLOR_CHAR ? 15 : 16;
        String lastColors = ChatColor.getLastColors(line.substring(0, prefixEnd));
        if(lastColors.equals(ChatColor.getLastColors(lastEntry))) {
            return line.substring(16);
        } else {
            String finalString = (getLastColor(lastColors) + line.substring(16));
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

    private String getLastColor(String lastColors) {
        if(lastColors.isEmpty()) {
            return "";
        }

        return lastColors.substring(lastColors.length() - 2);
    }
}
