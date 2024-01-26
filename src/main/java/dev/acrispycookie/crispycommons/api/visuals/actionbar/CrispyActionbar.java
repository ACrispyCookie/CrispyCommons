package dev.acrispycookie.crispycommons.api.visuals.actionbar;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;

import java.util.List;

public interface CrispyActionbar extends CrispyAccessibleVisual<TextElement> {
    void setDuration(int duration);
}
