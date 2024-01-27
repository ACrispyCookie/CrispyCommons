package dev.acrispycookie.crispycommons.api.visuals.actionbar;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.actionbar.SimpleActionbar;
import dev.acrispycookie.crispycommons.implementations.visuals.actionbar.wrappers.ActionbarData;
import org.bukkit.entity.Player;

import java.util.Set;

public interface CrispyActionbar extends CrispyAccessibleVisual<ActionbarData> {

    static ActionbarBuilder builder() {
        return new ActionbarBuilder();
    }

    class ActionbarBuilder extends AbstractVisualBuilder<CrispyActionbar> {

        private CrispyActionbar actionbar;
        private final ActionbarData data = new ActionbarData(null);

        public ActionbarBuilder setText(TextElement element) {
            this.data.setElement(element);
            this.data.getElement().setUpdate(() -> actionbar.update());
            return this;
        }

        @Override
        public CrispyActionbar build() {
            this.actionbar = new SimpleActionbar(data, receivers);
            return actionbar;
        }
    }
}
