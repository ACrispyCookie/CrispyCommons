package dev.acrispycookie.crispycommons.api.visuals.tablist;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;

import java.util.List;

public interface CrispyTablist extends CrispyAccessibleVisual<List<List<TextElement>>> {

    void setHeader(List<TextElement> element);
    void setFooter(List<TextElement> element);
    List<TextElement> getHeader();
    List<TextElement> getFooter();
}
