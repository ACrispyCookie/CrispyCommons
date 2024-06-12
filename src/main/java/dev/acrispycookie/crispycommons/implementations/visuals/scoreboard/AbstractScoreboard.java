package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard;

import dev.acrispycookie.crispycommons.api.visuals.scoreboard.CrispyScoreboard;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers.ScoreboardData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GlobalTextElement;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public abstract class AbstractScoreboard extends AbstractVisual<ScoreboardData> implements CrispyScoreboard {

    AbstractScoreboard(ScoreboardData data, Set<? extends OfflinePlayer> receivers, long timeToLive, UpdateMode updateMode) {
        super(data, receivers, timeToLive, updateMode);
    }

    @Override
    protected void prepareShow() {
        this.data.getTitle().start();
        this.data.getLines().forEach(GlobalTextElement::start);
    }

    @Override
    protected void prepareHide() {
        this.data.getTitle().stop();
        this.data.getLines().forEach(GlobalTextElement::stop);
    }

    @Override
    public void addLine(int index, GlobalTextElement line) {
        if (index > data.getLines().size()) {
            return;
        }

        data.addLine(index, line);
    }

    @Override
    public void addLine(GlobalTextElement line) {
        addLine(data.getLines().size(), line);
    }

    @Override
    public void removeLine(int index) {
        if(index >= data.getLines().size())
            return;

        data.removeLine(index);
    }

    @Override
    public void setLines(Collection<? extends GlobalTextElement> lines) {
        data.setLines(new ArrayList<>(lines));
    }

    public List<GlobalTextElement> getLines() {
        return data.getLines();
    }

    @Override
    public void setTitle(GlobalTextElement title) {
        data.setTitle(title);
    }

    @Override
    public GlobalTextElement getTitle() {
        return data.getTitle();
    }

}
