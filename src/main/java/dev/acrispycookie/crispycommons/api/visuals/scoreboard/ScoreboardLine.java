package dev.acrispycookie.crispycommons.api.visuals.scoreboard;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers.ScoreboardLineData;

public interface ScoreboardLine extends CrispyVisual<ScoreboardLineData> {

    int getPosition();
    void setScoreboard(AbstractScoreboard scoreboard);
    CrispyScoreboard getScoreboard();
}
