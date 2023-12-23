package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.utility.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.TextElement;

import java.util.ArrayList;

public class AbstractScoreboardLine implements ScoreboardLine {

    private final TextElement element;

    public AbstractScoreboardLine(String staticLine) {
        this.element = new SimpleTextElement(staticLine);
    }

    public AbstractScoreboardLine(ArrayList<String> frames, int period) {
        this.element = new TextElement(frames, period) {
            @Override
            protected void update() {

            }
        };
    }

    @Override
    public String getContent() {
        return element.getElement();
    }
}
