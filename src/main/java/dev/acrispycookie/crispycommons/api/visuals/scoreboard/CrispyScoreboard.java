package dev.acrispycookie.crispycommons.api.visuals.scoreboard;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

public interface CrispyScoreboard extends CrispyAccessibleVisual<List<AbstractScoreboardLine>> {

    Scoreboard getBukkitScoreboard();
    void setTitle(ScoreboardTitleLine title);
    ScoreboardTitleLine getTitle();
    void addLine(AbstractScoreboardLine line);
    void addLine(int index, AbstractScoreboardLine line);
    void removeLine(int index);
    void showLine(int index);
    void hideLine(int index);
}
