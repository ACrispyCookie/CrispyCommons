package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.scoreboard.AbstractScoreboardLine;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Collection;
import java.util.function.Supplier;

public class SimpleScoreboardLine extends AbstractScoreboardLine {

    public SimpleScoreboardLine(String staticLine) {
        super(new SimpleTextElement(staticLine));
    }

    public SimpleScoreboardLine(Collection<? extends String> frames, int period) {
        super(null);
        this.content = new TextElement(frames, period, false) {
            @Override
            protected void update() {
                SimpleScoreboardLine.this.update();
            }
        };
    }

    public SimpleScoreboardLine(Supplier<String> supplier, int period) {
        super(null);
        this.content = new TextElement(supplier, period, false) {
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

        String line = ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(getContent().getRaw()));
        String teamEntry = getEntry(line, bukkitScoreboard);
        Team team = bukkitScoreboard.registerNewTeam(String.valueOf(position));
        team.addEntry(teamEntry);
        team.setPrefix(getPrefix(line));
        team.setSuffix(getSuffix(line, teamEntry));
        obj.getScore(teamEntry).setScore(15 - position);
    }

    @Override
    protected void updateInternal() {
        Scoreboard bukkitScoreboard = scoreboard.getBukkitScoreboard();
        Objective obj = bukkitScoreboard.getObjective("[CrispyCommons]");
        Team team = bukkitScoreboard.getTeam(String.valueOf(position));

        String line = ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(getContent().getRaw()));
        line = line.substring(0, Math.min(line.length(), 32));
        String teamEntry = team.getEntries().iterator().next();
        String prefix = getPrefix(line);
        String suffix = getSuffix(line, teamEntry);
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
}
