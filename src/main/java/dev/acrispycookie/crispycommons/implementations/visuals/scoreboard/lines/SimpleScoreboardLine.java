package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.utility.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.TextElement;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleScoreboardLine implements ScoreboardLine {

    private final TextElement element;

    public SimpleScoreboardLine(String staticLine) {
        this.element = new SimpleTextElement(staticLine);
    }

    public SimpleScoreboardLine(ArrayList<String> frames, int period) {
        this.element = new TextElement(frames, period) {
            @Override
            protected void update() {

            }
        };
    }

    @Override
    public String getContent() {
        return element.getContent();
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void update() {

    }

    @Override
    public void addPlayer(Player player) {

    }

    @Override
    public void removePlayer(Player player) {

    }

    @Override
    public void setPlayers(Collection<? extends Player> players) {

    }

    @Override
    public List<Player> getPlayers() {
        return null;
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }
}
