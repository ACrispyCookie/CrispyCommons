package dev.acrispycookie.crispycommons.implementations.visuals.tablist;

import dev.acrispycookie.crispycommons.utility.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.utility.visual.CrispyAccessibleVisual;

import java.util.List;

public interface CrispyTablist extends CrispyAccessibleVisual<List<TextElement>> {

    void setHeader(TextElement element);
    void setFooter(TextElement element);
}
