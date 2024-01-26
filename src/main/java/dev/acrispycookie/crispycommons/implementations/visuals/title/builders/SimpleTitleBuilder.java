package dev.acrispycookie.crispycommons.implementations.visuals.title.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.title.SimpleTitle;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class SimpleTitleBuilder extends AbstractVisualBuilder<SimpleTitle> {

    private SimpleTextElement title;
    private SimpleTextElement subtitle;
    private int fadeIn;
    private int duration;
    private int fadeOut;

    public SimpleTitleBuilder(Set<? extends Player> receivers) {
        super(receivers);
    }

    public SimpleTitleBuilder() {

    }

    public SimpleTitleBuilder setTitle(String text) {
        this.title = new SimpleTextElement(text);
        return this;
    }

    public SimpleTitleBuilder setSubtitle(String text) {
        this.subtitle = new SimpleTextElement(text);
        return this;
    }

    public SimpleTitleBuilder setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
        return this;
    }

    public SimpleTitleBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public SimpleTitleBuilder setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
        return this;
    }

    @Override
    public SimpleTitle build() {
        return new SimpleTitle(Arrays.asList(title, subtitle), receivers, fadeIn, duration, fadeOut);
    }
}
