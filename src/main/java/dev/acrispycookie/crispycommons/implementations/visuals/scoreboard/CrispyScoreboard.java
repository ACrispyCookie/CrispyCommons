package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import dev.acrispycookie.crispycommons.utility.showable.CrispyShowable;

import java.util.List;

public interface CrispyScoreboard extends CrispyShowable {

    void setTitle(ScoreboardTitleLine title);
    void addLine(ScoreboardLine line);
    void addLine(int index, ScoreboardLine line);
    void removeLine(int index);
    List<ScoreboardLine> getLines();
}
