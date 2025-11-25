package dev.acrispycookie.crispycommons.implementations.visual.nametag.data;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.element.OwnedElement;
import dev.acrispycookie.crispycommons.implementations.element.type.NameTagElement;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A data class representing the visual data required to display a name tag.
 * <p>
 * {@code NameTagData} encapsulates all elements related to a player's name tag, including the prefix,
 * suffix, below-name value, below-name text, and above-name text. It also provides a method to
 * ensure that all necessary data is set before displaying the name tag.
 * </p>
 */
public class NameTagData implements VisualData {

    /**
     * The player associated with this name tag.
     */
    private Player player;

    /**
     * The prefix element of the name tag.
     */
    private OwnedElement<NameTagElement<String, ?>> prefix;

    /**
     * The suffix element of the name tag.
     */
    private OwnedElement<NameTagElement<String, ?>> suffix;

    /**
     * The value to be displayed below the player's name.
     */
    private OwnedElement<NameTagElement<Integer, ?>> belowNameValue;

    /**
     * The text to be displayed below the player's name.
     */
    private OwnedElement<NameTagElement<String, ?>> belowName;

    /**
     * The text to be displayed above the player's name.
     */
    private OwnedElement<NameTagElement<String, ?>> aboveName;

    /**
     * Constructs a new {@code NameTagData} instance with the specified elements.
     *
     * @param player the player associated with this name tag.
     * @param prefix the prefix element of the name tag.
     * @param suffix the suffix element of the name tag.
     * @param belowNameValue the value to be displayed below the player's name.
     * @param belowName the text to be displayed below the player's name.
     * @param aboveName the text to be displayed above the player's name.
     */
    public NameTagData(@Nullable Player player, @Nullable NameTagElement<String, ?> prefix, @Nullable NameTagElement<String, ?> suffix,
                       @Nullable NameTagElement<Integer, ?> belowNameValue, @Nullable NameTagElement<String, ?> belowName,
                       @Nullable NameTagElement<String, ?> aboveName) {
        this.player = player;
        this.prefix = prefix != null ? new OwnedElement<>(prefix, this) : null;
        this.suffix = suffix != null ? new OwnedElement<>(suffix, this) : null;
        this.belowNameValue = belowNameValue != null ? new OwnedElement<>(belowNameValue, this) : null;
        this.belowName = belowName != null ? new OwnedElement<>(belowName, true) : null;
        this.aboveName = aboveName != null ? new OwnedElement<>(aboveName, true) : null;
    }

    /**
     * Retrieves the player associated with this name tag.
     *
     * @return the player associated with this name tag.
     */
    public @Nullable Player getPlayer() {
        return player;
    }

    /**
     * Retrieves the prefix element of the name tag.
     *
     * @return the prefix element of the name tag.
     */
    public @Nullable OwnedElement<NameTagElement<String, ?>> getPrefix() {
        return prefix;
    }

    /**
     * Retrieves the suffix element of the name tag.
     *
     * @return the suffix element of the name tag.
     */
    public @Nullable OwnedElement<NameTagElement<String, ?>> getSuffix() {
        return suffix;
    }

    /**
     * Retrieves the text to be displayed below the player's name.
     *
     * @return the below-name text element.
     */
    public @Nullable OwnedElement<NameTagElement<String, ?>> getBelowName() {
        return belowName;
    }

    /**
     * Retrieves the value to be displayed below the player's name.
     *
     * @return the below-name value element.
     */
    public @Nullable OwnedElement<NameTagElement<Integer, ?>> getBelowNameValue() {
        return belowNameValue;
    }

    /**
     * Retrieves the text to be displayed above the player's name.
     *
     * @return the above-name text element.
     */
    public @Nullable OwnedElement<NameTagElement<String, ?>> getAboveName() {
        return aboveName;
    }

    /**
     * Sets the player associated with this name tag.
     *
     * @param player the player to associate with this name tag.
     */
    public void setPlayer(@NotNull Player player) {
        this.player = player;
    }

    /**
     * Sets the prefix element of the name tag.
     *
     * @param prefix the prefix element to set.
     */
    public void setPrefix(@NotNull NameTagElement<String, ?> prefix) {
        this.prefix = new OwnedElement<>(prefix, this, "prefix");
    }

    /**
     * Sets the suffix element of the name tag.
     *
     * @param suffix the suffix element to set.
     */
    public void setSuffix(@NotNull NameTagElement<String, ?> suffix) {
        this.suffix = new OwnedElement<>(suffix, this, "suffix");
    }

    /**
     * Sets the text to be displayed below the player's name.
     *
     * @param belowName the below-name text element to set.
     */
    public void setBelowName(@NotNull NameTagElement<String, ?> belowName) {
        this.belowName = new OwnedElement<>(belowName, this, "below-name");
    }

    /**
     * Sets the value to be displayed below the player's name.
     *
     * @param belowNameValue the below-name value element to set.
     */
    public void setBelowNameValue(@NotNull NameTagElement<Integer, ?> belowNameValue) {
        this.belowNameValue = new OwnedElement<>(belowNameValue, this, "below-name-value");
    }

    /**
     * Sets the text to be displayed above the player's name.
     *
     * @param aboveName the above-name text element to set.
     */
    public void setAboveName(@NotNull NameTagElement<String, ?> aboveName) {
        this.aboveName = new OwnedElement<>(aboveName, this, "above-name");
    }

    /**
     * Asserts that the visual data is ready for display.
     * <p>
     * This method checks if the necessary elements for displaying the name tag are set. If any required
     * element is missing, a {@link VisualNotReadyException} is thrown.
     * </p>
     *
     * @param player the player for whom the visual data should be ready.
     * @throws VisualNotReadyException if any required element is not set or improperly configured.
     */
    @Override
    public void assertReady(@NotNull Player player) {
        if (this.player == null)
            throw new VisualNotReadyException("The name tag's player was not set!");
        if (prefix == null)
            throw new VisualNotReadyException("The name tag's prefix was not set!");
        if (suffix == null)
            throw new VisualNotReadyException("The name tag's suffix was not set!");
        if (belowNameValue != null && belowName == null)
            throw new VisualNotReadyException("The below name value was set while below name display wasn't set!");
        if (belowName != null && belowNameValue == null)
            throw new VisualNotReadyException("The below name display was set while below name value wasn't set!");
    }
}

