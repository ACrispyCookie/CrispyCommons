package dev.acrispycookie.crispycommons.implementations.visuals.message;

import dev.acrispycookie.crispycommons.utility.visual.CrispyAccessibleVisual;

public interface CrispyMessage extends CrispyAccessibleVisual<String> {
    void setText(String text);
    void setFadeIn(int fadeIn);
    void setDuration(int duration);
    void setFadeOut(int fadeOut);

}
