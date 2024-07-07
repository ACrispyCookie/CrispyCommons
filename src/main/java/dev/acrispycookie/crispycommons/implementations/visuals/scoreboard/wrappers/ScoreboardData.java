package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalTextElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ScoreboardData implements VisualData {

    private GlobalTextElement title;
    private List<GlobalTextElement> lines;

    public ScoreboardData(GlobalTextElement title, Collection<? extends GlobalTextElement> lines) {
        this.title = title;
        this.lines = new ArrayList<>(lines);
    }

    public GlobalTextElement getTitle() {
        return title;
    }

    public void setTitle(GlobalTextElement title) {
        this.title = title;
    }

    public List<GlobalTextElement> getLines() {
        return lines;
    }

    public void addLine(GlobalTextElement line) {
        this.lines.add(line);
    }

    public void addLine(int index, GlobalTextElement line) {
        this.lines.add(index, line);
    }

    public void removeLine(int index) {
        this.lines.remove(index);
    }

    public void setLines(List<GlobalTextElement> lines) {
        this.lines = lines;
    }

    @Override
    public void assertReady() {
        if (title == null)
            throw new VisualNotReadyException("The scoreboard title was not set!");
        if (lines == null)
            throw new VisualNotReadyException("The scoreboard lines were not set!");
    }
}
