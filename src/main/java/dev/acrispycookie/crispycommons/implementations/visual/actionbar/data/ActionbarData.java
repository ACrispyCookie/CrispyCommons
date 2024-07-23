package dev.acrispycookie.crispycommons.implementations.visual.actionbar.data;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class ActionbarData implements VisualData {

    private TextElement<?> text;

    public ActionbarData(TextElement<?> text) {
        this.text = text;
    }

    public TextElement<?> getText() {
        return text;
    }

    public void setText(TextElement<?> text) {
        this.text = text;
    }

    @Override
    public void assertReady(Player p) {
        if (text.getFromContext(OfflinePlayer.class, p) == null)
            throw new VisualNotReadyException("The actionbar text was not set!");
    }
}
