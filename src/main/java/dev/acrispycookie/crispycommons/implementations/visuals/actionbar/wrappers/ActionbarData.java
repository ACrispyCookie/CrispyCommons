package dev.acrispycookie.crispycommons.implementations.visuals.actionbar.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;

public class ActionbarData implements VisualData {

    private TextElement element;
    private int duration;

    public ActionbarData(TextElement element, int duration) {
        this.element = element;
        this.duration = duration;
    }

    public TextElement getElement() {
        return element;
    }

    public void setElement(TextElement element) {
        this.element = element;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
