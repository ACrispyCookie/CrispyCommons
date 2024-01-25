package dev.acrispycookie.crispycommons.api.visuals.scoreboard;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.StringElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;

public interface ScoreboardLine extends CrispyVisual<StringElement> {

    int getPosition();
    void setScoreboard(AbstractCrispyScoreboard scoreboard);
    CrispyScoreboard getScoreboard();
}
