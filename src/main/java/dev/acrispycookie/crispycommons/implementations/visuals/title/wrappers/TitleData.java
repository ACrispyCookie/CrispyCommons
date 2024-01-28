package dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers;

import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.DynamicElement;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.types.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;

public class TitleData implements VisualData {

    private TextElement title;
    private TextElement subtitle;
    private int fadeIn;
    private int fadeOut;
    private int smallestPeriod;

    public TitleData(TextElement title, TextElement subtitle, int fadeIn, int fadeOut) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.fadeOut = fadeOut;
        this.smallestPeriod = DynamicElement.getMinimumPeriod(title, subtitle);
    }

    public TextElement getTitle() {
        return title;
    }

    public TextElement getSubtitle() {
        return subtitle;
    }

    public int getFadeIn() {
        return fadeIn;
    }

    public int getFadeOut() {
        return fadeOut;
    }

    public void setTitle(TextElement title) {
        this.title = title;
        this.smallestPeriod = DynamicElement.getMinimumPeriod(title, subtitle);
    }

    public void setSubtitle(TextElement subtitle) {
        this.subtitle = subtitle;
        this.smallestPeriod = DynamicElement.getMinimumPeriod(title, subtitle);
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
}
