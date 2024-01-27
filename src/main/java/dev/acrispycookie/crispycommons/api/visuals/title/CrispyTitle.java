package dev.acrispycookie.crispycommons.api.visuals.title;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.title.SimpleTitle;
import dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers.TitleData;


public interface CrispyTitle extends CrispyAccessibleVisual<TitleData> {
    static TitleBuilder builder() {
        return new TitleBuilder();
    }
    void setTitle(TextElement text);
    void setSubtitle(TextElement text);
    void setFadeIn(int fadeIn);
    void setDuration(int duration);
    void setFadeOut(int fadeOut);
    TextElement getTitle();
    TextElement getSubtitle();
    int getFadeIn();
    int getDuration();
    int getFadeOut();

    class TitleBuilder extends AbstractVisualBuilder<CrispyTitle> {

        private CrispyTitle title;
        protected final TitleData data = new TitleData(null, null, 0, 0,0);

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

        public TitleBuilder setDuration(int duration) {
            this.data.setDuration(duration);
            return this;
        }

        public TitleBuilder setFadeOut(int fadeOut) {
            this.data.setFadeOut(fadeOut);
            return this;
        }

        public CrispyTitle build() {
            this.title = new SimpleTitle(data, receivers);
            return title;
        }
    }
}
