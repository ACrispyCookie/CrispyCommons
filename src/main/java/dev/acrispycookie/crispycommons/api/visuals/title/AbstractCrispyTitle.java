package dev.acrispycookie.crispycommons.api.visuals.title;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractCrispyAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers.TitleData;
import org.bukkit.entity.Player;

import java.util.Set;

public abstract class AbstractCrispyTitle extends AbstractCrispyAccessibleVisual<TitleData> implements CrispyTitle {

    protected abstract void showInternal();
    protected abstract void hideInternal();

    public AbstractCrispyTitle(TitleData data, Set<? extends Player> receivers) {
        super(data, receivers);
    }

    @Override
    public void show() {
        if (isDisplayed)
            return;

        isDisplayed = true;
        data.getTitle().start();
        data.getSubtitle().start();
        showInternal();
    }

    @Override
    public void hide() {
        if (!isDisplayed)
            return;

        isDisplayed = false;
        data.getTitle().stop();
        data.getSubtitle().stop();
        hideInternal();
    }

    @Override
    public void update() {

    }

    @Override
    public void setTitle(TextElement text) {
        this.data.setTitle(text);
    }

    @Override
    public void setSubtitle(TextElement text) {
        this.data.setSubtitle(text);
    }

    @Override
    public void setFadeIn(int fadeIn) {
        this.data.setFadeIn(fadeIn);
    }

    @Override
    public void setDuration(int duration) {
        this.data.setDuration(duration);
    }

    @Override
    public void setFadeOut(int fadeOut) {
        this.data.setFadeOut(fadeOut);
    }

    @Override
    public void setPeriod(int period) {
        this.data.setPeriod(period);
    }

    @Override
    public TextElement getTitle() {
        return this.data.getTitle();
    }

    @Override
    public TextElement getSubtitle() {
        return this.data.getSubtitle();
    }

    @Override
    public int getFadeIn() {
        return this.data.getFadeIn();
    }

    @Override
    public int getDuration() {
        return this.data.getDuration();
    }

    @Override
    public int getFadeOut() {
        return this.data.getFadeOut();
    }

    @Override
    public int getPeriod() {
        return this.data.getPeriod();
    }
}
