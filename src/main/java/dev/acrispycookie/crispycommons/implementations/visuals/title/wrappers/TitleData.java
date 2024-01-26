package dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;

public class TitleData implements VisualData {

    private TextElement title;
    private TextElement subtitle;
    private int fadeIn;
    private int duration;
    private int fadeOut;
    private int period;

    public TitleData(TextElement title, TextElement subtitle, int fadeIn, int duration, int fadeOut, int period) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.duration = duration;
        this.fadeOut = fadeOut;
        this.period = period;
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

    public int getPeriod() {
        return period;
    }

    public void setTitle(TextElement title) {
        this.title = title;
    }

    public void setSubtitle(TextElement subtitle) {
        this.subtitle = subtitle;
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

    public void setPeriod(int period) {
        this.period = period;
    }
}
