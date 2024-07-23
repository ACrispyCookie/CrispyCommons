package dev.acrispycookie.crispycommons.implementations.visual.scoreboard;

import dev.acrispycookie.crispycommons.api.visual.scoreboard.CrispyScoreboard;
import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visual.scoreboard.data.ScoreboardData;
import dev.acrispycookie.crispycommons.implementations.element.AbstractDynamicElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public abstract class AbstractScoreboard extends AbstractVisual<ScoreboardData> implements CrispyScoreboard {

    protected abstract void updateLines(int oldSize);
    protected abstract void removeLineInternal(int index);
    protected abstract void addLineInternal(int index);

    AbstractScoreboard(ScoreboardData data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, UpdateMode updateMode, boolean isPublic) {
        super(data, receivers, timeToLive, updateMode, isPublic);
    }

    @Override
    protected void prepareShow() {
        this.data.getTitle().start();
        this.data.getLines().forEach(TextElement::start);
    }

    @Override
    protected void prepareHide() {
        this.data.getTitle().stop();
        this.data.getLines().forEach(TextElement::stop);
    }

    @Override
    public void addLine(int index, TextElement<?> line) {
        if (index > data.getLines().size()) {
            return;
        }

        line.setUpdate(this::update);
        if (isAnyoneWatching())
            line.start();
        data.addLine(index, line);
        addLineInternal(index);
    }

    @Override
    public void addLine(TextElement<?> line) {
        addLine(data.getLines().size(), line);
    }

    @Override
    public void removeLine(int index) {
        if(index >= data.getLines().size())
            return;

        if (isAnyoneWatching())
            data.getLines().get(index).stop();
        data.removeLine(index);
        removeLineInternal(index);
    }

    @Override
    public void setLines(Collection<? extends TextElement<?>> lines) {
        lines.forEach((l) -> l.setUpdate(this::update));
        if (isAnyoneWatching()) {
            data.getLines().forEach(DynamicElement::stop);
            lines.forEach(AbstractDynamicElement::start);
        }
        int oldSize = data.getLines().size();
        data.setLines(new ArrayList<>(lines));
        updateLines(oldSize);
    }

    public List<TextElement<?>> getLines() {
        return data.getLines();
    }

    @Override
    public void setTitle(TextElement<?> title) {
        if (isAnyoneWatching()) {
            data.getTitle().stop();
            title.start();
        }
        data.setTitle(title);
    }

    @Override
    public TextElement<?> getTitle() {
        return data.getTitle();
    }

}
