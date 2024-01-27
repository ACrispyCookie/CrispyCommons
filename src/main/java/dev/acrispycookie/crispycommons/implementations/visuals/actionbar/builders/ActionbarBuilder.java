package dev.acrispycookie.crispycommons.implementations.visuals.actionbar.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.actionbar.CrispyActionbar;
import dev.acrispycookie.crispycommons.implementations.visuals.actionbar.wrappers.ActionbarData;
import dev.acrispycookie.crispycommons.implementations.visuals.actionbar.SimpleActionbar;
import org.bukkit.entity.Player;

import java.util.Set;

public class ActionbarBuilder extends AbstractVisualBuilder<CrispyActionbar> {

    private SimpleActionbar actionbar;
    private TextElement text;
    private int duration;

    public ActionbarBuilder(Set<? extends Player> receivers) {
        super(receivers);
    }

    public ActionbarBuilder() {

    }

    public ActionbarBuilder setText(TextElement element) {
        this.text = element;
        this.text.setUpdate(() -> actionbar.update());
        return this;
    }

    public ActionbarBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public CrispyActionbar build() {
        ActionbarData data = new ActionbarData(text, duration);
        this.actionbar = new SimpleActionbar(data, receivers);
        return actionbar;
    }
}
