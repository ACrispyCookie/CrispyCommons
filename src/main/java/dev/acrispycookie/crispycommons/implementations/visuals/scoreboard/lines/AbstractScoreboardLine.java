package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.CrispyScoreboard;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.utility.showable.AbstractCrispyShowable;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.*;

public abstract class AbstractScoreboardLine extends AbstractCrispyShowable<String> implements ScoreboardLine {

    protected int index;
    protected TextElement element;
    protected CrispyScoreboard scoreboard;
    protected abstract void show(Player player);
    protected abstract void hide(Player player);
    protected abstract void update(Player player);
    protected abstract void updatePosition(Player player);

    public AbstractScoreboardLine(TextElement element, Collection<? extends Player> receivers) {
        super(new HashSet<>(receivers));
        this.element = element;
    }

    @Override
    public void show() {
        if (isDisplayed || scoreboard == null) {
            return;
        }

        element.start();
        receivers.forEach(this::show);
        isDisplayed = true;
        if (scoreboard.isDisplayed())
            scoreboard.updateLinePosition();
    }

    @Override
    public void hide() {
        if (!isDisplayed) {
            return;
        }

        element.stop();
        receivers.forEach(this::hide);
        isDisplayed = false;
        if (scoreboard.isDisplayed())
            scoreboard.updateLinePosition();
    }

    @Override
    public void update() {
        if (isDisplayed) {
            receivers.forEach(this::update);
        }
    }

    @Override
    public void updatePosition() {
        if (isDisplayed) {
            receivers.forEach(this::updatePosition);
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

    protected int getLineIndex(Player player) {
        int index = 0;

        for(ScoreboardLine line : scoreboard.getCurrentContent()) {
            if(line.equals(this)) {
                break;
            }
            if(line.isDisplayed() && line.getPlayers().contains(player)) {
                index++;
            }
        }

        return index;
    }
}
