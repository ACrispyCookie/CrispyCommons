package dev.acrispycookie.crispycommons.api.visuals.title;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;

public interface CrispyTItle extends CrispyAccessibleVisual<String> {
    void setText(String text);
    void setFadeIn(int fadeIn);
    void setDuration(int duration);
    void setFadeOut(int fadeOut);

}
