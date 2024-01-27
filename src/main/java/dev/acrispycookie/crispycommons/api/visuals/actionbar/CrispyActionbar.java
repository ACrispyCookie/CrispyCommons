package dev.acrispycookie.crispycommons.api.visuals.actionbar;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.actionbar.SimpleActionbar;
import dev.acrispycookie.crispycommons.implementations.visuals.actionbar.wrappers.ActionbarData;

public interface CrispyActionbar extends CrispyVisual {

    static ActionbarBuilder builder() {
        return new ActionbarBuilder();
    }
    void setText(TextElement text);
    TextElement getText();

    class ActionbarBuilder extends AbstractVisualBuilder<CrispyActionbar> {

        private CrispyActionbar actionbar;
        private final ActionbarData data = new ActionbarData(null);

        public ActionbarBuilder setText(TextElement element) {
            this.data.setText(element);
            this.data.getText().setUpdate(() -> actionbar.update());
            return this;
        }

        @Override
        public CrispyActionbar build() {
            this.actionbar = new SimpleActionbar(data, receivers, timeToLive);
            return actionbar;
        }
    }
}
