package dev.acrispycookie.crispycommons.api.visual.actionbar;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visual.actionbar.SimpleActionbar;
import dev.acrispycookie.crispycommons.implementations.visual.actionbar.data.ActionbarData;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;

public interface CrispyActionbar extends CrispyVisual {

    static ActionbarBuilder builder() {
        return new ActionbarBuilder();
    }
    void setText(TextElement<?> text);
    TextElement<?> getText();

    class ActionbarBuilder extends AbstractVisualBuilder<CrispyActionbar> {

        private final ActionbarData data = new ActionbarData(null);

        public ActionbarBuilder setText(TextElement<?> element) {
            this.data.setText(element);
            this.data.getText().setUpdate(() -> toBuild.update());
            return this;
        }

        @Override
        public CrispyActionbar build() {
            this.toBuild = new SimpleActionbar(data, receivers, timeToLive, isPublic);
            return toBuild;
        }
    }
}
