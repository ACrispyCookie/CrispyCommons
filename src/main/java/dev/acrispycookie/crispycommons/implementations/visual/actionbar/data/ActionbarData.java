package dev.acrispycookie.crispycommons.implementations.visual.actionbar.data;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * A data class representing the visual data needed to display an action bar.
 * <p>
 * {@code ActionbarData} encapsulates the text element that will be displayed
 * in the action bar. This class also provides a method to ensure the data is
 * ready to be displayed.
 * </p>
 */
public class ActionbarData implements VisualData {

    /**
     * The text element to be displayed in the action bar.
     */
    private TextElement<?> text;

    /**
     * Constructs a new {@code ActionbarData} instance with the specified text element.
     *
     * @param text the {@link TextElement} to be displayed in the action bar.
     */
    public ActionbarData(TextElement<?> text) {
        this.text = text;
    }

    /**
     * Retrieves the text element that will be displayed in the action bar.
     *
     * @return the {@link TextElement} to be displayed.
     */
    public TextElement<?> getText() {
        return text;
    }

    /**
     * Sets the text element to be displayed in the action bar.
     *
     * @param text the {@link TextElement} to set.
     */
    public void setText(TextElement<?> text) {
        this.text = text;
    }

    /**
     * Asserts that the visual data is ready for display.
     * <p>
     * This method checks if the text element can be retrieved from the context
     * of the specified player. If the text is not set or cannot be retrieved,
     * a {@link VisualNotReadyException} is thrown.
     * </p>
     *
     * @param p the player for whom the visual data should be ready.
     * @throws VisualNotReadyException if the text element is not set or cannot be retrieved.
     */
    @Override
    public void assertReady(Player p) {
        if (text.getFromContext(OfflinePlayer.class, p) == null) {
            throw new VisualNotReadyException("The actionbar text was not set!");
        }
    }
}

