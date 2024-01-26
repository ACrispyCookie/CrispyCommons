package dev.acrispycookie.crispycommons.api.visuals.title;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractCrispyAccessibleVisual;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public abstract class AbstractCrispyTitle extends AbstractCrispyAccessibleVisual<List<TextElement>> implements CrispyTitle {

    protected abstract void showInternal();
    protected abstract void hideInternal();
    protected int fadeIn;
    protected int duration;
    protected int fadeOut;

    public AbstractCrispyTitle(List<TextElement> text, Set<? extends Player> receivers, int fadeIn, int duration, int fadeOut) {
        super(text, receivers);
        this.fadeIn = fadeIn;
        this.duration = duration;
        this.fadeOut = fadeOut;
    }

    @Override
    public void show() {
        if (isDisplayed)
            return;

        isDisplayed = true;
        content.forEach(TextElement::start);
        showInternal();
    }

    @Override
    public void hide() {
        if (!isDisplayed)
            return;

        isDisplayed = false;
        content.forEach(TextElement::stop);
        hideInternal();
    }

    @Override
    public void update() {

    }

    @Override
    public void setTitle(TextElement text) {
        this.content.set(0, text);
    }

    @Override
    public void setSubtitle(TextElement text) {
        this.content.set(1, text);
    }

    @Override
    public void setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
    }

    @Override
    public TextElement getTitle() {
        return this.content.get(0);
    }

    @Override
    public TextElement getSubtitle() {
        return this.content.get(1);
    }

    @Override
    public int getFadeIn() {
        return fadeIn;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getFadeOut() {
        return fadeOut;
    }
}
