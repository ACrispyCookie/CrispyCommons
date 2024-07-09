package dev.acrispycookie.crispycommons.implementations.visuals.actionbar.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalTextElement;
import org.bukkit.entity.Player;

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

    @Override
    public void assertReady(Player p) {
        if (text == null || (text instanceof PersonalTextElement && ((PersonalTextElement) text).getRaw(p) == null))
            throw new VisualNotReadyException("The actionbar text was not set!");
    }
}
