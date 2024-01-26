package dev.acrispycookie.crispycommons.api.visuals.scoreboard;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractCrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers.ScoreboardLineData;

public abstract class AbstractScoreboardLine extends AbstractCrispyVisual<ScoreboardLineData> implements ScoreboardLine {

    protected abstract void initialize();
    protected abstract void updateInternal();

    public AbstractScoreboardLine(ScoreboardLineData element) {
        super(element);
    }

    protected void show(int position) {
        this.data.setPosition(position);
        initialize();
        this.data.getElement().start();
        isDisplayed = true;
    }

    protected void hide() {
        this.data.getElement().stop();
        isDisplayed = false;
    }

    protected void update() {
        if (isDisplayed) {
            updateInternal();
        }
    }

    protected void setDisplayed(boolean displayed) {
        isDisplayed = displayed;
    }

    @Override
    public void setScoreboard(AbstractCrispyScoreboard scoreboard) {
        this.data.setScoreboard(scoreboard);
    }

    @Override
    public CrispyScoreboard getScoreboard() {
        return this.data.getScoreboard();
    }

    @Override
    public int getPosition() {
        return this.data.getPosition();
    }
}
