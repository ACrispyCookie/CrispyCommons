package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.SimpleScoreboardLine;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ScoreboardData implements VisualData {

    private ScoreboardTitleLine title;
    private List<SimpleScoreboardLine> lines;
    private Scoreboard bukkitScoreboard;

    public ScoreboardData(ScoreboardTitleLine title, Collection<? extends SimpleScoreboardLine> lines, Scoreboard bukkitScoreboard) {
        this.title = title;
        this.lines = new ArrayList<>(lines);
        this.bukkitScoreboard = bukkitScoreboard;
    }

    public ScoreboardTitleLine getTitle() {
        return title;
    }

    public void setTitle(ScoreboardTitleLine title) {
        this.title = title;
    }

    public List<SimpleScoreboardLine> getLines() {
        return lines;
    }

    public void setLines(List<SimpleScoreboardLine> lines) {
        this.lines = lines;
    }

    public Scoreboard getBukkitScoreboard() {
        return bukkitScoreboard;
    }

    public void setBukkitScoreboard(Scoreboard bukkitScoreboard) {
        this.bukkitScoreboard = bukkitScoreboard;
    }
}
