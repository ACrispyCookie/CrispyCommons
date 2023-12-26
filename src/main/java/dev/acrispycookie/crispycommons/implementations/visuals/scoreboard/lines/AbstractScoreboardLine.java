package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.CrispyScoreboard;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.utility.showable.AbstractCrispyVisual;

import java.util.*;

public abstract class AbstractScoreboardLine extends AbstractCrispyVisual<String> implements ScoreboardLine {

    protected TextElement element;
    protected CrispyScoreboard scoreboard;
    protected int position;
    protected abstract void showInternal();
    protected abstract void hideInternal();
    protected abstract void updateInternal();

    public AbstractScoreboardLine(TextElement element) {
        this.element = element;
    }

    public void show() {
        if (isDisplayed || scoreboard == null) {
            return;
        }

        element.start();
        showInternal();
        isDisplayed = true;
        if (scoreboard.isDisplayed())
            scoreboard.updateLinePosition();
    }

    public void hide() {
        if (!isDisplayed || !scoreboard.isDisplayed()) {
            return;
        }

        element.stop();
        hideInternal();
        isDisplayed = false;
        scoreboard.updateLinePosition();
    }

    public void update() {
        if (isDisplayed) {
            updateInternal();
        }
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

    @Override
    public void setPosition(int position) {
        if(isDisplayed) {
            hideInternal();
            this.position = position;
            showInternal();
        } else {
            this.position = position;
        }
    }

    @Override
    public int getNewPosition() {
        int index = 0;

        for(ScoreboardLine line : scoreboard.getCurrentContent()) {
            if(line.equals(this)) {
                break;
            }
            if(line.isDisplayed()) {
                index++;
            }
        }

        return index;
    }
}
