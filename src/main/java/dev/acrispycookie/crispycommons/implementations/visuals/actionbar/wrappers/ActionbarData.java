package dev.acrispycookie.crispycommons.implementations.visuals.actionbar.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalTextElement;

public class ActionbarData implements VisualData {

    private GlobalTextElement text;

    public ActionbarData(GlobalTextElement text) {
        this.text = text;
    }

    public GlobalTextElement getText() {
        return text;
    }

    public void setText(GlobalTextElement text) {
        this.text = text;
    }

    @Override
    public void assertReady() {
        if (text == null)
            throw new VisualNotReadyException("The actionbar text was not set!");
    }
}
