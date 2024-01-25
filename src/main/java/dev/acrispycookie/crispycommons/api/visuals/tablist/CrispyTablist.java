package dev.acrispycookie.crispycommons.api.visuals.tablist;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.StringElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;

import java.util.List;

public interface CrispyTablist extends CrispyAccessibleVisual<List<List<StringElement>>> {

    void setHeader(List<StringElement> element);
    void setFooter(List<StringElement> element);
    List<StringElement> getHeader();
    List<StringElement> getFooter();
}
