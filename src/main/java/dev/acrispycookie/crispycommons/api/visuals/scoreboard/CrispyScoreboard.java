package dev.acrispycookie.crispycommons.api.visuals.scoreboard;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.PublicScoreboard;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.SimpleScoreboard;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers.ScoreboardData;

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
        private boolean isPublic = false;

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

        public ScoreboardBuilder setPublic(boolean isPublic) {
            this.isPublic = isPublic;
            return this;
        }

        public CrispyScoreboard build() {
            if(isPublic) {
                toBuild = new PublicScoreboard(data, receivers, timeToLive);
            } else {
                toBuild = new SimpleScoreboard(data, receivers, timeToLive);
            }

            return toBuild;
        }


    }
}
