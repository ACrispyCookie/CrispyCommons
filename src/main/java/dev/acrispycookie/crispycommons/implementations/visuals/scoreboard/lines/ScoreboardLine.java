package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.CrispyScoreboard;
import dev.acrispycookie.crispycommons.utility.showable.CrispyShowable;

public interface ScoreboardLine extends CrispyShowable<String> {

    void updatePosition();
    void setScoreboard(CrispyScoreboard scoreboard);
    CrispyScoreboard getScoreboard();
    String getCurrentContent();
}
