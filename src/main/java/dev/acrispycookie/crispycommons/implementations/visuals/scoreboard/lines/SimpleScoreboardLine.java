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
        team.setSuffix(getSuffix(line));
        obj.getScore(teamEntry).setScore(15 - position);
    }

    @Override
    protected void updateInternal() {
        System.out.println("Updating line: " + position);
        Scoreboard bukkitScoreboard = scoreboard.getBukkitScoreboard();
        Objective obj = bukkitScoreboard.getObjective("[CrispyCommons]");
        Team team = bukkitScoreboard.getTeam(String.valueOf(position));

        String teamEntry = team.getEntries().iterator().next();
        String line = ChatColor.translateAlternateColorCodes('&', getCurrentContent());
        team.setPrefix(getPrefix(line));
        team.setSuffix(getSuffix(line));
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

    private String getSuffix(String line) {
        if (line.length() <= 16) {
            return "";
        }
        return line.substring(16);
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
}
