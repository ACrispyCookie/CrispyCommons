package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.implementations;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.AbstractScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.SimpleScoreboardLine;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Supplier;

public class SimpleScoreboardBuilder {

    private boolean isPublic = false;
    private final ScoreboardTitleLine title;
    private final Collection<AbstractScoreboardLine> lines = new ArrayList<>();
    private final Collection<Player> players = new ArrayList<>();


    public SimpleScoreboardBuilder(String staticTitle) {
        title = new ScoreboardTitleLine(staticTitle);
    }

    public SimpleScoreboardBuilder(String staticTitle, Collection<? extends Player> players) {
        title = new ScoreboardTitleLine(staticTitle);
        this.players.addAll(players);
    }

    public SimpleScoreboardBuilder(ArrayList<String> titleFrames, int period) {
        title = new ScoreboardTitleLine(titleFrames, period);
    }

    public SimpleScoreboardBuilder(ArrayList<String> titleFrames, int period, Collection<? extends Player> players) {
        title = new ScoreboardTitleLine(titleFrames, period);
        this.players.addAll(players);
    }

    public SimpleScoreboardBuilder addPlayer(Player player) {
        players.add(player);
        return this;
    }

    public SimpleScoreboardBuilder setPlayers(Collection<? extends Player> players) {
        this.players.addAll(players);
        return this;
    }

    public SimpleScoreboardBuilder addTextLine(String text) {
        SimpleScoreboardLine line = new SimpleScoreboardLine(text);
        lines.add(line);
        return this;
    }

    public SimpleScoreboardBuilder addAnimatedTextLine(Collection<? extends String> frames, int period) {
        SimpleScoreboardLine line = new SimpleScoreboardLine(frames, period);
        lines.add(line);
        return this;
    }

    public SimpleScoreboardBuilder addAnimatedTextLine(Supplier<String> supplier, int period) {
        SimpleScoreboardLine line = new SimpleScoreboardLine(supplier, period);
        lines.add(line);
        return this;
    }

    public SimpleScoreboardBuilder setPublic(boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    public SimpleScoreboard build() {
        SimpleScoreboard scoreboard;

        if(isPublic) {
            scoreboard = new PublicScoreboard(title, lines, players);
        } else {
            scoreboard = new SimpleScoreboard(title, lines, players);
        }

        return scoreboard;
    }


}
