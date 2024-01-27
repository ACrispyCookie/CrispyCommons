package dev.acrispycookie.crispycommons.implementations.visuals.actionbar.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;

public class ActionbarData implements VisualData {

    private TextElement text;

    public ActionbarData(TextElement text) {
        this.text = text;
    }

    public TextElement getText() {
        return text;
    }

    public void setText(TextElement text) {
        this.text = text;
    }
}
