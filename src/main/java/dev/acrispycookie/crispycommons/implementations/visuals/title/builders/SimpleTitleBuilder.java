package dev.acrispycookie.crispycommons.implementations.visuals.title.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.title.SimpleTitle;
import dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers.TitleData;
import org.bukkit.entity.Player;

import java.util.Set;

public class SimpleTitleBuilder extends AbstractVisualBuilder<SimpleTitle> {

    private TitleData data;
    private TextElement title;
    private TextElement subtitle;
    private int fadeIn;
    private int duration;
    private int fadeOut;

    public SimpleTitleBuilder(Set<? extends Player> receivers) {
        super(receivers);
    }

    public SimpleTitleBuilder() {

    }

    public SimpleTitleBuilder setTitle(String text) {
        this.title = TextElement.simple(text);
        return this;
    }

    public SimpleTitleBuilder setSubtitle(String text) {
        this.subtitle = TextElement.simple(text);
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
        data = new TitleData(title, subtitle, fadeIn, duration, fadeOut, -1);
        return new SimpleTitle(data, receivers);
    }
}
