package dev.acrispycookie.crispycommons.api.visuals.scoreboard;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyNonVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.AbstractScoreboard;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers.ScoreboardLineData;

public interface ScoreboardLine extends CrispyNonVisual<ScoreboardLineData> {

    int getPosition();
    void setScoreboard(AbstractScoreboard scoreboard);
    CrispyScoreboard getScoreboard();
}
