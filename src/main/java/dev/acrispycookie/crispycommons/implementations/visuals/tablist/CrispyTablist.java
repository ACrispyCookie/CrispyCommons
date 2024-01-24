package dev.acrispycookie.crispycommons.implementations.visuals.tablist;

import dev.acrispycookie.crispycommons.utility.elements.implementations.text.StringElement;
import dev.acrispycookie.crispycommons.utility.visual.CrispyAccessibleVisual;

import java.util.List;

public interface CrispyTablist extends CrispyAccessibleVisual<List<List<StringElement>>> {

    void setHeader(List<StringElement> element);
    void setFooter(List<StringElement> element);
    List<StringElement> getHeader();
    List<StringElement> getFooter();
}
