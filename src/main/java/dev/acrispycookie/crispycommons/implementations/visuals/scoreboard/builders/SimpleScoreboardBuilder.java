package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.builders;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.SimpleCrispyScoreboard;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.AbstractScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardLine;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class SimpleScoreboardBuilder {

    private final JavaPlugin plugin;
    private final Player player;
    private String title;
    private ArrayList<ScoreboardLine> lines = new ArrayList<>();
    private int updateInterval;

    public SimpleScoreboardBuilder(JavaPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public SimpleScoreboardBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public SimpleScoreboardBuilder setLines(ArrayList<ScoreboardLine> lines) {
        this.lines = lines;
        return this;
    }

    public SimpleScoreboardBuilder addLine(ScoreboardLine line) {
        lines.add(line);
        return this;
    }

    public SimpleScoreboardBuilder addLine(int index, ScoreboardLine line) {
        lines.add(index, line);
        return this;
    }

    public SimpleScoreboardBuilder removeLine(int index) {
        lines.remove(index);
        return this;
    }

    public SimpleScoreboardBuilder removeLine(ScoreboardLine line) {
        lines.remove(line);
        return this;
    }

    public SimpleScoreboardBuilder setUpdateInterval(int updateInterval) {
        this.updateInterval = updateInterval;
        return this;
    }

    public SimpleCrispyScoreboard build() {
        return new SimpleCrispyScoreboard(player, title, lines, updateInterval);
    }

}
