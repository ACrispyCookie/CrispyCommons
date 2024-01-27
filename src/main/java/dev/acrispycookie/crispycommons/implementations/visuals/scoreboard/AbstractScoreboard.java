package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.scoreboard.CrispyScoreboard;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers.ScoreboardData;
import org.bukkit.OfflinePlayer;

import java.util.*;

public abstract class AbstractScoreboard extends AbstractVisual<ScoreboardData> implements CrispyScoreboard {

    AbstractScoreboard(ScoreboardData data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive);
    }

    @Override
    public void prepareShow() {
        this.data.getLines().forEach(TextElement::start);
    }

    @Override
    public void prepareHide() {
        this.data.getLines().forEach(TextElement::stop);
    }

    @Override
    public void prepareUpdate() {

    }

    @Override
    public void addLine(int index, TextElement line) {
        if (index > data.getLines().size()) {
            return;
        }

        data.addLine(index, line);
    }

    @Override
    public void addLine(TextElement line) {
        addLine(data.getLines().size(), line);
    }

    @Override
    public void removeLine(int index) {
        if(index >= data.getLines().size())
            return;

        data.removeLine(index);
    }

    @Override
    public void setLines(Collection<? extends TextElement> lines) {
        data.setLines(new ArrayList<>(lines));
    }

    public List<TextElement> getLines() {
        return data.getLines();
    }

    @Override
    public void setTitle(TextElement title) {
        data.setTitle(title);
    }

    @Override
    public TextElement getTitle() {
        return data.getTitle();
    }

}
