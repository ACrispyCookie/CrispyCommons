package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.scoreboard.CrispyScoreboard;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.PublicScoreboard;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.SimpleScoreboard;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.SimpleScoreboardLine;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers.ScoreboardData;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Supplier;

public class ScoreboardBuilder extends AbstractVisualBuilder<CrispyScoreboard> {

    private boolean isPublic = false;
    private ScoreboardTitleLine title;
    private final Collection<SimpleScoreboardLine> lines = new ArrayList<>();


    public ScoreboardBuilder() {

    }

    public ScoreboardBuilder(Collection<? extends Player> players) {
        super(players);
    }

    public ScoreboardBuilder setTitle(TextElement title) {
        this.title = new ScoreboardTitleLine(title);
        return this;
    }

    public ScoreboardBuilder addTextLine(TextElement text) {
        SimpleScoreboardLine line = new SimpleScoreboardLine(text);
        lines.add(line);
        return this;
    }

    public ScoreboardBuilder setPublic(boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    public CrispyScoreboard build() {
        SimpleScoreboard scoreboard;
        ScoreboardData data = new ScoreboardData(title, lines, null);

        if(isPublic) {
            scoreboard = new PublicScoreboard(data, receivers);
        } else {
            scoreboard = new SimpleScoreboard(data, receivers);
        }

        return scoreboard;
    }


}
