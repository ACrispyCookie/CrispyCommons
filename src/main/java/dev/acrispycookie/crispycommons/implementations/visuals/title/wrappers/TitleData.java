package dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;

public class TitleData implements VisualData {

    private TextElement title;
    private TextElement subtitle;
    private int fadeIn;
    private int duration;
    private int fadeOut;
    private int smallestPeriod;

    public TitleData(TextElement title, TextElement subtitle, int fadeIn, int duration, int fadeOut) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.duration = duration;
        this.fadeOut = fadeOut;
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

    public int getDuration() {
        return duration;
    }

    public int getFadeOut() {
        return fadeOut;
    }

    public void setTitle(TextElement title) {
        this.title = title;
        this.smallestPeriod = subtitle != null ? Math.min(title.getPeriod(),subtitle.getPeriod()) : title.getPeriod();
    }

    public void setSubtitle(TextElement subtitle) {
        this.subtitle = subtitle;
        this.smallestPeriod = title != null ? Math.min(title.getPeriod(),subtitle.getPeriod()) : subtitle.getPeriod();
    }

    public void setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
    }

    public int getSmallestPeriod() {
        return smallestPeriod;
    }
}
