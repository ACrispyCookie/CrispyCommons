package dev.acrispycookie.crispycommons.implementations.visual.actionbar.data;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.element.OwnedElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    private OwnedElement<TextElement<?>> text;

    /**
     * Constructs a new {@code ActionbarData} instance with the specified text element.
     *
     * @param text the {@link TextElement} to be displayed in the action bar.
     */
    public ActionbarData(@Nullable TextElement<?> text) {
        this.text = text != null ? new OwnedElement<>(text, this) : null;
    }

    /**
     * Retrieves the text element that will be displayed in the action bar.
     *
     * @return the {@link TextElement} to be displayed.
     */
    public @Nullable OwnedElement<TextElement<?>> getText() {
        return text;
    }

    /**
     * Sets the text element to be displayed in the action bar.
     *
     * @param text the {@link TextElement} to set.
     */
    public void setText(@NotNull TextElement<?> text) {
        this.text = new OwnedElement<>(text, this);
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
    public void assertReady(@NotNull Player p) {
        if (text == null)
            throw new VisualNotReadyException("The actionbar text was not set!");
    }
}

