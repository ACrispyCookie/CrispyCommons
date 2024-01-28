package dev.acrispycookie.crispycommons.implementations.visuals.actionbar.wrappers;

import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.types.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import org.jetbrains.annotations.NotNull;

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
