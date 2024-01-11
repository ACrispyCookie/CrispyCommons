package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.utility.visual.CrispyVisual;

public interface ScoreboardLine extends CrispyVisual<String> {

    int getPosition();
    void setScoreboard(AbstractCrispyScoreboard scoreboard);
    CrispyScoreboard getScoreboard();
    String getCurrentContent();
}
