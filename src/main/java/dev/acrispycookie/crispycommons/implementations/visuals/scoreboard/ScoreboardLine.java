package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.CrispyScoreboard;
import dev.acrispycookie.crispycommons.utility.showable.CrispyVisual;

public interface ScoreboardLine extends CrispyVisual<String> {

    int getPosition();
    void setScoreboard(AbstractCrispyScoreboard scoreboard);
    CrispyScoreboard getScoreboard();
    String getCurrentContent();
}
