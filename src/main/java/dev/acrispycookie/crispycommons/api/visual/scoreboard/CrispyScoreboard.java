package dev.acrispycookie.crispycommons.api.visual.scoreboard;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visual.scoreboard.SimpleScoreboard;
import dev.acrispycookie.crispycommons.implementations.visual.scoreboard.data.ScoreboardData;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface CrispyScoreboard extends CrispyVisual {

    static ScoreboardBuilder builder() {
        return new ScoreboardBuilder();
    }
    void setTitle(TextElement<?> title);
    void addLine(TextElement<?> line);
    void addLine(int index, TextElement<?> line);
    void removeLine(int index);
    void setLines(Collection<? extends TextElement<?>> lines);
    TextElement<?> getTitle();
    List<TextElement<?>> getLines();

    class ScoreboardBuilder extends AbstractVisualBuilder<CrispyScoreboard> {

        private final ScoreboardData data = new ScoreboardData(null, new ArrayList<>());

        public ScoreboardBuilder setTitle(TextElement<?> title) {
            this.data.setTitle(title);
            title.setUpdate(() -> toBuild.update());
            return this;
        }

        public ScoreboardBuilder addTextLine(TextElement<?> text) {
            this.data.addLine(text);
            text.setUpdate(() -> toBuild.update());
            return this;
        }

        public CrispyScoreboard build() {
            toBuild = new SimpleScoreboard(data, receivers, timeToLive, isPublic);

            return toBuild;
        }


    }
}
