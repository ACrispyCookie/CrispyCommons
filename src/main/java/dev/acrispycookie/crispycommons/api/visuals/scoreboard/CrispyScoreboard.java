package dev.acrispycookie.crispycommons.api.visuals.scoreboard;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.PublicScoreboard;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.SimpleScoreboard;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.wrappers.ScoreboardData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalTextElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface CrispyScoreboard extends CrispyVisual {

    static ScoreboardBuilder builder() {
        return new ScoreboardBuilder();
    }
    void setTitle(GlobalTextElement title);
    void addLine(GlobalTextElement line);
    void addLine(int index, GlobalTextElement line);
    void removeLine(int index);
    void setLines(Collection<? extends GlobalTextElement> lines);
    GlobalTextElement getTitle();
    List<GlobalTextElement> getLines();

    class ScoreboardBuilder extends AbstractVisualBuilder<CrispyScoreboard> {

        private CrispyScoreboard scoreboard;
        private final ScoreboardData data = new ScoreboardData(null, new ArrayList<>());
        private boolean isPublic = false;

        public ScoreboardBuilder setTitle(GlobalTextElement title) {
            this.data.setTitle(title);
            title.setUpdate(() -> scoreboard.update());
            return this;
        }

        public ScoreboardBuilder addTextLine(GlobalTextElement text) {
            this.data.addLine(text);
            text.setUpdate(() -> scoreboard.update());
            return this;
        }

        public ScoreboardBuilder setPublic(boolean isPublic) {
            this.isPublic = isPublic;
            return this;
        }

        public CrispyScoreboard build() {
            if(isPublic) {
                scoreboard = new PublicScoreboard(data, receivers, timeToLive);
            } else {
                scoreboard = new SimpleScoreboard(data, receivers, timeToLive);
            }

            return scoreboard;
        }


    }
}
