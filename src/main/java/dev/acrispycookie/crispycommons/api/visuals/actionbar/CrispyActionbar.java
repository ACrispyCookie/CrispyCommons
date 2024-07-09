package dev.acrispycookie.crispycommons.api.visuals.actionbar;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.actionbar.SimpleActionbar;
import dev.acrispycookie.crispycommons.implementations.visuals.actionbar.wrappers.ActionbarData;

public interface CrispyActionbar extends CrispyVisual {

    static ActionbarBuilder builder() {
        return new ActionbarBuilder();
    }
    void setText(TextElement text);
    TextElement getText();

    class ActionbarBuilder extends AbstractVisualBuilder<CrispyActionbar> {

        private final ActionbarData data = new ActionbarData(null);

        public ActionbarBuilder setText(TextElement element) {
            this.data.setText(element);
            this.data.getText().setUpdate(() -> toBuild.update());
            return this;
        }

        @Override
        public CrispyActionbar build() {
            this.toBuild = new SimpleActionbar(data, receivers, timeToLive);
            return toBuild;
        }
    }
}
