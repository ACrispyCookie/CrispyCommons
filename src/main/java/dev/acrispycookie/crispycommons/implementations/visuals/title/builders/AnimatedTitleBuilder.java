package dev.acrispycookie.crispycommons.implementations.visuals.title.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.title.AnimatedTitle;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;

public class AnimatedTitleBuilder extends AbstractVisualBuilder<AnimatedTitle> {
    private TextElement title;
    private TextElement subtitle;
    private int fadeIn;
    private int duration;
    private int fadeOut;
    private int period = 1;

    public AnimatedTitleBuilder(Set<? extends Player> receivers) {
        super(receivers);
    }

    public AnimatedTitleBuilder() {

    }

    public AnimatedTitleBuilder setTitle(String text) {
        this.title = new SimpleTextElement(text);
        return this;
    }

    public AnimatedTitleBuilder setAnimatedTitle(Collection<? extends String> frames, int period) {
        this.title = new TextElement(frames, period, false) {
            @Override
            protected void update() {

            }
        };
        return this;
    }

    public AnimatedTitleBuilder setAnimatedTitle(Supplier<? extends String> supplier, int period) {
        this.title = new TextElement(supplier, period, false) {
            @Override
            protected void update() {

            }
        };
        return this;
    }

    public AnimatedTitleBuilder setSubtitle(String text) {
        this.subtitle = new SimpleTextElement(text);
        return this;
    }

    public AnimatedTitleBuilder setAnimatedSubtitle(Collection<? extends String> frames, int period) {
        this.subtitle = new TextElement(frames, period, false) {
            @Override
            protected void update() {
            }
        };
        return this;
    }

    public AnimatedTitleBuilder setAnimatedSubtitle(Supplier<? extends String> supplier, int period) {
        this.subtitle = new TextElement(supplier, period, false) {
            @Override
            protected void update() {
            }
        };
        return this;
    }

    public AnimatedTitleBuilder setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
        return this;
    }

    public AnimatedTitleBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public AnimatedTitleBuilder setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
        return this;
    }

    public AnimatedTitleBuilder setPeriod(int period) {
        this.period = period;
        return this;
    }

    @Override
    public AnimatedTitle build() {
        return new AnimatedTitle(Arrays.asList(title, subtitle), receivers, fadeIn, duration, fadeOut, period);
    }
}
