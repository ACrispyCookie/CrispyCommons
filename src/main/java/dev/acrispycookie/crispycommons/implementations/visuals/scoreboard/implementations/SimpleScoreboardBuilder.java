package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.implementations;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.AbstractScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.CrispyScoreboard;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.SimpleScoreboardLine;
import dev.acrispycookie.crispycommons.utility.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.utility.builder.VisualBuilder;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Supplier;

public class SimpleScoreboardBuilder extends AbstractVisualBuilder<CrispyScoreboard> {

    private boolean isPublic = false;
    private final ScoreboardTitleLine title;
    private final Collection<AbstractScoreboardLine> lines = new ArrayList<>();


    public SimpleScoreboardBuilder(String staticTitle) {
        title = new ScoreboardTitleLine(staticTitle);
    }

    public SimpleScoreboardBuilder(String staticTitle, Collection<? extends Player> players) {
        super(players);
        title = new ScoreboardTitleLine(staticTitle);
    }

    public SimpleScoreboardBuilder(ArrayList<String> titleFrames, int period) {
        title = new ScoreboardTitleLine(titleFrames, period);
    }

    public SimpleScoreboardBuilder(ArrayList<String> titleFrames, int period, Collection<? extends Player> players) {
        super(players);
        title = new ScoreboardTitleLine(titleFrames, period);
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

    public CrispyScoreboard build() {
        SimpleScoreboard scoreboard;

        if(isPublic) {
            scoreboard = new PublicScoreboard(title, lines, receivers);
        } else {
            scoreboard = new SimpleScoreboard(title, lines, receivers);
        }

        return scoreboard;
    }


}
