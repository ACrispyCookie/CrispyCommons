package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalTextElement;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ScoreboardData implements VisualData {

    private TextElement title;
    private List<TextElement> lines;

    public ScoreboardData(TextElement title, Collection<? extends TextElement> lines) {
        this.title = title;
        this.lines = new ArrayList<>(lines);
    }

    public TextElement getTitle() {
        return title;
    }

    public void setTitle(TextElement title) {
        this.title = title;
    }

    public List<TextElement> getLines() {
        return lines;
    }

    public void addLine(TextElement line) {
        this.lines.add(line);
    }

    public void addLine(int index, TextElement line) {
        this.lines.add(index, line);
    }

    public void removeLine(int index) {
        this.lines.remove(index);
    }

    public void setLines(List<TextElement> lines) {
        this.lines = lines;
    }

    @Override
    public void assertReady(Player player) {
        if (title == null || (title instanceof PersonalTextElement && ((PersonalTextElement) title).getRaw(player) == null))
            throw new VisualNotReadyException("The scoreboard title was not set!");
        if (lines == null)
            throw new VisualNotReadyException("The scoreboard lines were not set!");
    }
}
