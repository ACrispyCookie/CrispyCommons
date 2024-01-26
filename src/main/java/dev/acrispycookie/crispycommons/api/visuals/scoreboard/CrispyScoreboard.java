package dev.acrispycookie.crispycommons.api.visuals.scoreboard;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.SimpleScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers.ScoreboardData;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

public interface CrispyScoreboard extends CrispyAccessibleVisual<ScoreboardData> {

    Scoreboard getBukkitScoreboard();
    void setTitle(ScoreboardTitleLine title);
    ScoreboardTitleLine getTitle();
    void addLine(SimpleScoreboardLine line);
    void addLine(int index, SimpleScoreboardLine line);
    void removeLine(int index);
    void showLine(int index);
    void hideLine(int index);
}
