package dev.acrispycookie.crispycommons.api.visuals.scoreboard;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.StringElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractCrispyVisual;

public abstract class AbstractScoreboardLine extends AbstractCrispyVisual<StringElement> implements ScoreboardLine {
    protected AbstractCrispyScoreboard scoreboard;
    protected int position;
    protected abstract void initialize();
    protected abstract void updateInternal();

    public AbstractScoreboardLine(StringElement element) {
        super(element);
    }

    protected void show(int position) {
        this.position = position;
        initialize();
        content.start();
        isDisplayed = true;
    }

    protected void hide() {
        content.stop();
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
        this.scoreboard = scoreboard;
    }

    @Override
    public CrispyScoreboard getScoreboard() {
        return scoreboard;
    }

    @Override
    public int getPosition() {
        return position;
    }
}
