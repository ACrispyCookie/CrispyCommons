package dev.acrispycookie.crispycommons.api.visuals.title;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
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
    void setTitle(TextElement<?> text);
    void setSubtitle(TextElement<?> text);
    void setFadeIn(GeneralElement<Integer, ?> fadeIn);
    void setFadeOut(GeneralElement<Integer, ?> fadeOut);
    TextElement<?> getTitle();
    TextElement<?> getSubtitle();
    GeneralElement<Integer, ?> getFadeIn();
    GeneralElement<Integer, ?> getFadeOut();

    abstract class TitleBuilder extends AbstractVisualBuilder<CrispyTitle> {

        protected final TitleData data = new TitleData(null, null, GeneralElement.simple(0), GeneralElement.simple(0));

        public TitleBuilder setTitle(TextElement<?> text) {
            text.setUpdate(() -> toBuild.update());
            this.data.setTitle(text);
            return this;
        }

        public TitleBuilder setSubtitle(TextElement<?> text) {
            text.setUpdate(() -> toBuild.update());
            this.data.setSubtitle(text);
            return this;
        }

        public TitleBuilder setFadeIn(GeneralElement<Integer, ?> fadeIn) {
            this.data.setFadeIn(fadeIn);
            this.data.getFadeIn().setUpdate(() -> toBuild.update());
            return this;
        }

        public TitleBuilder setFadeOut(GeneralElement<Integer, ?> fadeOut) {
            this.data.setFadeOut(fadeOut);
            this.data.getFadeOut().setUpdate(() -> toBuild.update());
            return this;
        }
    }

    class SimpleTitleBuilder extends TitleBuilder {
        public SimpleTitle build() {
            this.toBuild = new SimpleTitle(data, receivers, timeToLive);
            return (SimpleTitle) toBuild;
        }
    }

    class UpdatingTitleBuilder extends TitleBuilder {
        public UpdatingTitle build() {
            this.toBuild = new UpdatingTitle(data, receivers, timeToLive);
            return (UpdatingTitle) toBuild;
        }
    }
}
