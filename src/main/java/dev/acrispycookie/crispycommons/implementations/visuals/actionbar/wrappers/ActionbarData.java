package dev.acrispycookie.crispycommons.implementations.visuals.actionbar.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;

public class ActionbarData implements VisualData {

    private TextElement element;

    public ActionbarData(TextElement element) {
        this.element = element;
    }

    public TextElement getElement() {
        return element;
    }

    public void setElement(TextElement element) {
        this.element = element;
    }
}
