package dev.acrispycookie.crispycommons.api.visuals.actionbar;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.actionbar.wrappers.ActionbarData;

public interface CrispyActionbar extends CrispyAccessibleVisual<ActionbarData> {
    void setDuration(int duration);
}
