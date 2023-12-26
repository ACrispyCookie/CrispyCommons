package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.AbstractScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import dev.acrispycookie.crispycommons.utility.showable.CrispyAccessibleVisual;
import dev.acrispycookie.crispycommons.utility.showable.CrispyVisual;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

public interface CrispyScoreboard extends CrispyAccessibleVisual<List<ScoreboardLine>> {

    void updateLinePosition();
    Scoreboard getBukkitScoreboard();
    void setTitle(ScoreboardTitleLine title);
    ScoreboardTitleLine getTitle();
    void addLine(AbstractScoreboardLine line);
    void addLine(int index, AbstractScoreboardLine line);
    void removeLine(int index);
}
