package dev.acrispycookie.crispycommons.api.visuals.title;

import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.types.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.title.SimpleTitle;
import dev.acrispycookie.crispycommons.implementations.visuals.title.UpdatingTitle;
import dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers.TitleData;


public interface CrispyTitle extends CrispyVisual {
    static SimpleTitleBuilder simpleBuilder() {
        return new SimpleTitleBuilder();
    }
    static UpdatingTitleBuilder updatingBuilder() {
        return new UpdatingTitleBuilder();
    }
    void setTitle(TextElement text);
    void setSubtitle(TextElement text);
    void setFadeIn(int fadeIn);
    void setFadeOut(int fadeOut);
    TextElement getTitle();
    TextElement getSubtitle();
    int getFadeIn();
    int getFadeOut();

    abstract class TitleBuilder extends AbstractVisualBuilder<CrispyTitle> {

        protected CrispyTitle title;
        protected final TitleData data = new TitleData(null, null, 0,0);

        public TitleBuilder setTitle(TextElement text) {
            text.setUpdate(() -> title.update());
            this.data.setTitle(text);
            return this;
        }

        public TitleBuilder setSubtitle(TextElement text) {
            text.setUpdate(() -> title.update());
            this.data.setSubtitle(text);
            return this;
        }

        public TitleBuilder setFadeIn(int fadeIn) {
            this.data.setFadeIn(fadeIn);
            return this;
        }

        public TitleBuilder setFadeOut(int fadeOut) {
            this.data.setFadeOut(fadeOut);
            return this;
        }
    }

    class SimpleTitleBuilder extends TitleBuilder {
        public SimpleTitle build() {
            this.title = new SimpleTitle(data, receivers, timeToLive);
            return (SimpleTitle) title;
        }
    }

    class UpdatingTitleBuilder extends TitleBuilder {
        public UpdatingTitle build() {
            this.title = new UpdatingTitle(data, receivers, timeToLive);
            return (UpdatingTitle) title;
        }
    }
}
