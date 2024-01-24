package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.utility.elements.implementations.text.StringElement;
import dev.acrispycookie.crispycommons.utility.visual.CrispyVisual;

public interface ScoreboardLine extends CrispyVisual<StringElement> {

    int getPosition();
    void setScoreboard(AbstractCrispyScoreboard scoreboard);
    CrispyScoreboard getScoreboard();
}
