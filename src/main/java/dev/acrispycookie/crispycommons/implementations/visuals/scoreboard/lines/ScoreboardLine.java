package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.CrispyScoreboard;
import dev.acrispycookie.crispycommons.utility.showable.CrispyVisual;

public interface ScoreboardLine extends CrispyVisual<String> {

    int getNewPosition();
    int getPosition();
    void setPosition(int index);
    void setScoreboard(CrispyScoreboard scoreboard);
    CrispyScoreboard getScoreboard();
    String getCurrentContent();
}
