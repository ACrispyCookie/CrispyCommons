package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.CrispyScoreboard;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.utility.showable.AbstractCrispyShowable;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.*;

public abstract class AbstractScoreboardLine extends AbstractCrispyShowable<String> implements ScoreboardLine {

    protected TextElement element;
    protected CrispyScoreboard scoreboard;
    protected abstract void show(Scoreboard bukkitScoreboard);
    protected abstract void hide(Scoreboard bukkitScoreboard);
    protected abstract void update(Scoreboard bukkitScoreboard);

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
        receivers.forEach((p) -> show(scoreboard.getBukkitScoreboard(p)));
        isDisplayed = true;
        scoreboard.update();
    }

    @Override
    public void hide() {
        if (!isDisplayed) {
            return;
        }

        element.stop();
        receivers.forEach((p) -> hide(scoreboard.getBukkitScoreboard(p)));
        isDisplayed = false;
        scoreboard.update();
    }

    @Override
    public void update() {
        if (isDisplayed) {
            receivers.forEach((p) -> update(scoreboard.getBukkitScoreboard(p)));
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
}
