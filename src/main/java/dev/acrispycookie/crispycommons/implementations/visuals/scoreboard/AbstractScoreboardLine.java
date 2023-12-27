package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.utility.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.utility.showable.AbstractCrispyVisual;

public abstract class AbstractScoreboardLine extends AbstractCrispyVisual<String> implements ScoreboardLine {

    protected TextElement element;
    protected CrispyScoreboard scoreboard;
    protected int position;
    protected abstract void initialize();
    protected abstract void updateInternal();

    public AbstractScoreboardLine(TextElement element) {
        this.element = element;
    }

    protected void show(int position) {
        this.position = position;
        initialize();
        element.start();
        isDisplayed = true;
    }

    protected void hide() {
        element.stop();
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
    public String getCurrentContent() {
        return element.getContent();
    }

    @Override
    public void setScoreboard(CrispyScoreboard scoreboard) {
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
