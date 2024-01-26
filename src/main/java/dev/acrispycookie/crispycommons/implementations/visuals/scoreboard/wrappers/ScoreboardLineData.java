package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.visuals.scoreboard.CrispyScoreboard;

public class ScoreboardLineData implements VisualData {

    private TextElement element;
    private int position;

    private CrispyScoreboard scoreboard;

    public ScoreboardLineData(TextElement element, int position, CrispyScoreboard scoreboard) {
        this.element = element;
        this.position = position;
        this.scoreboard = scoreboard;
    }

    public TextElement getElement() {
        return element;
    }

    public void setElement(TextElement element) {
        this.element = element;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public CrispyScoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(CrispyScoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }
}
