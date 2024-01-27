package dev.acrispycookie.crispycommons.api.visuals.scoreboard;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.PublicScoreboard;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.SimpleScoreboard;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.ScoreboardTitleLine;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.SimpleScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers.ScoreboardData;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public interface CrispyScoreboard extends CrispyVisual {

    static ScoreboardBuilder builder() {
        return new ScoreboardBuilder();
    }
    Scoreboard getBukkitScoreboard();
    void setTitle(ScoreboardTitleLine title);
    ScoreboardTitleLine getTitle();
    void addLine(SimpleScoreboardLine line);
    void addLine(int index, SimpleScoreboardLine line);
    void removeLine(int index);
    void showLine(int index);
    void hideLine(int index);

    class ScoreboardBuilder extends AbstractVisualBuilder<CrispyScoreboard> {

        private final ScoreboardData data = new ScoreboardData(null, new ArrayList<>(), null);
        private boolean isPublic = false;

        public ScoreboardBuilder setTitle(TextElement title) {
            this.data.setTitle(new ScoreboardTitleLine(title));
            return this;
        }

        public ScoreboardBuilder addTextLine(TextElement text) {
            List<SimpleScoreboardLine> newLines = this.data.getLines();
            newLines.add(new SimpleScoreboardLine(text));
            this.data.setLines(newLines);
            return this;
        }

        public ScoreboardBuilder setPublic(boolean isPublic) {
            this.isPublic = isPublic;
            return this;
        }

        public CrispyScoreboard build() {
            SimpleScoreboard scoreboard;

            if(isPublic) {
                scoreboard = new PublicScoreboard(data, receivers, timeToLive);
            } else {
                scoreboard = new SimpleScoreboard(data, receivers, timeToLive);
            }

            return scoreboard;
        }


    }
}
