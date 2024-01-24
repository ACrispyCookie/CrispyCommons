package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.utility.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.utility.visual.AbstractCrispyVisual;

public abstract class AbstractScoreboardLine extends AbstractCrispyVisual<TextElement> implements ScoreboardLine {
    protected AbstractCrispyScoreboard scoreboard;
    protected int position;
    protected abstract void initialize();
    protected abstract void updateInternal();

    public AbstractScoreboardLine(TextElement element) {
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
