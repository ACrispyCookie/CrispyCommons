package dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.wrappers.elements.CrispyElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.DynamicElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GlobalTextElement;

public class TitleData implements VisualData {

    private GlobalTextElement title;
    private GlobalTextElement subtitle;
    private int fadeIn;
    private int fadeOut;
    private int smallestPeriod;

    public TitleData(GlobalTextElement title, GlobalTextElement subtitle, int fadeIn, int fadeOut) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.fadeOut = fadeOut;
    }

    public GlobalTextElement getTitle() {
        return title;
    }

    public GlobalTextElement getSubtitle() {
        return subtitle;
    }

    public int getFadeIn() {
        return fadeIn;
    }

    public int getFadeOut() {
        return fadeOut;
    }

    public void setTitle(GlobalTextElement title) {
        this.title = title;
        this.smallestPeriod = CrispyElement.getMinimumPeriod(title, subtitle);
    }

    public void setSubtitle(GlobalTextElement subtitle) {
        this.subtitle = subtitle;
        this.smallestPeriod = CrispyElement.getMinimumPeriod(title, subtitle);
    }

    public void setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    public void setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
    }

    public int getSmallestPeriod() {
        return smallestPeriod;
    }

    @Override
    public void assertReady() {
        if (title == null)
            throw new VisualNotReadyException("The title was not set!");
        if (subtitle == null)
            throw new VisualNotReadyException("The subtitle was not set!");
        this.smallestPeriod = CrispyElement.getMinimumPeriod(title, subtitle);
    }
}
