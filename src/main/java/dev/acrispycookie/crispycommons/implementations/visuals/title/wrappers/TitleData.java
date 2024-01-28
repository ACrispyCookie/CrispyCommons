package dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers;

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
        this.smallestPeriod = findSmallestPeriod();
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
        this.smallestPeriod = findSmallestPeriod();
    }

    public void setSubtitle(TextElement subtitle) {
        this.subtitle = subtitle;
        this.smallestPeriod = findSmallestPeriod();
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

    private int findSmallestPeriod() {
        if (title != null && subtitle != null) {
            int smallestPeriod = title.getPeriod() < 0 ? -1 : title.getPeriod();
            smallestPeriod = subtitle.getPeriod() < 0
                    ?
                    smallestPeriod < 0 ? -1 : smallestPeriod
                    :
                    smallestPeriod < 0 ? subtitle.getPeriod() : Math.min(smallestPeriod, subtitle.getPeriod());
            return smallestPeriod;
        } else {
            return -1;
        }
    }
}
