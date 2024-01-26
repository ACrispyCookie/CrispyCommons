package dev.acrispycookie.crispycommons.api.visuals.title;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;

import java.util.List;

public interface CrispyTitle extends CrispyAccessibleVisual<List<TextElement>> {
    void setTitle(TextElement text);
    void setSubtitle(TextElement text);
    void setFadeIn(int fadeIn);
    void setDuration(int duration);
    void setFadeOut(int fadeOut);

}
