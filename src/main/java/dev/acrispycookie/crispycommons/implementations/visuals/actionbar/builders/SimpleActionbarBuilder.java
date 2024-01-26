package dev.acrispycookie.crispycommons.implementations.visuals.actionbar.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.actionbar.CrispyActionbar;
import dev.acrispycookie.crispycommons.implementations.visuals.actionbar.SimpleActionbar;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;

public class SimpleActionbarBuilder extends AbstractVisualBuilder<CrispyActionbar> {

    private SimpleActionbar actionbar;
    private TextElement text;
    private int duration;

    public SimpleActionbarBuilder(Set<? extends Player> receivers) {
        super(receivers);
    }

    public SimpleActionbarBuilder() {

    }

    public SimpleActionbarBuilder setText(String text) {
        this.text = new SimpleTextElement(text);
        return this;
    }

    public SimpleActionbarBuilder setText(Collection<? extends String> frames, int period) {
        this.text = new TextElement(frames, period, false) {
            @Override
            protected void update() {
                actionbar.update();
            }
        };
        return this;
    }

    public SimpleActionbarBuilder setText(Supplier<? extends String> supplier, int period) {
        this.text = new TextElement(supplier, period, false) {
            @Override
            protected void update() {
                actionbar.update();
            }
        };
        return this;
    }

    public SimpleActionbarBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public CrispyActionbar build() {
        this.actionbar = new SimpleActionbar(text, receivers, duration);
        return actionbar;
    }
}
