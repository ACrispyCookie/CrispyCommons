package dev.acrispycookie.crispycommons.api.visuals.tablist;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.wrappers.TablistData;

import java.util.List;

public interface CrispyTablist extends CrispyAccessibleVisual<TablistData> {

    void setHeader(List<TextElement> element);
    void setFooter(List<TextElement> element);
    List<TextElement> getHeader();
    List<TextElement> getFooter();
}
