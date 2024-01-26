package dev.acrispycookie.crispycommons.api.visuals.nametag;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;

public interface CrispyNametag extends CrispyAccessibleVisual<NameTagData> {
    void setPrefix(TextElement prefix);
    void setSuffix(TextElement suffix);
    void setBelowName(TextElement belowName);
    void setAboveName(TextElement aboveName);
    TextElement getPrefix();
    TextElement getSuffix();
    TextElement getBelowName();
    TextElement getAboveName();
}
