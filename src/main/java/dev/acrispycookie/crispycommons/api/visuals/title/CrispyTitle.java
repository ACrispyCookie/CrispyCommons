package dev.acrispycookie.crispycommons.api.visuals.title;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers.TitleData;

import java.util.List;

public interface CrispyTitle extends CrispyAccessibleVisual<TitleData> {
    void setTitle(TextElement text);
    void setSubtitle(TextElement text);
    void setFadeIn(int fadeIn);
    void setDuration(int duration);
    void setFadeOut(int fadeOut);
    void setPeriod(int period);
    TextElement getTitle();
    TextElement getSubtitle();
    int getFadeIn();
    int getDuration();
    int getFadeOut();
    int getPeriod();
}
