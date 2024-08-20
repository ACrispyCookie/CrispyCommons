package dev.acrispycookie.crispycommons.implementations.visual.nametag.data;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.element.type.NameTagElement;
import org.bukkit.entity.Player;

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
    private NameTagElement<String, ?> prefix;

    /**
     * The suffix element of the name tag.
     */
    private NameTagElement<String, ?> suffix;

    /**
     * The value to be displayed below the player's name.
     */
    private NameTagElement<Integer, ?> belowNameValue;

    /**
     * The text to be displayed below the player's name.
     */
    private NameTagElement<String, ?> belowName;

    /**
     * The text to be displayed above the player's name.
     */
    private NameTagElement<String, ?> aboveName;

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
    public NameTagData(Player player, NameTagElement<String, ?> prefix, NameTagElement<String, ?> suffix,
                       NameTagElement<Integer, ?> belowNameValue, NameTagElement<String, ?> belowName,
                       NameTagElement<String, ?> aboveName) {
        this.player = player;
        this.prefix = prefix;
        this.suffix = suffix;
        this.belowNameValue = belowNameValue;
        this.belowName = belowName;
        this.aboveName = aboveName;
    }

    /**
     * Retrieves the player associated with this name tag.
     *
     * @return the player associated with this name tag.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player associated with this name tag.
     *
     * @param player the player to associate with this name tag.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Retrieves the prefix element of the name tag.
     *
     * @return the prefix element of the name tag.
     */
    public NameTagElement<String, ?> getPrefix() {
        return prefix;
    }

    /**
     * Sets the prefix element of the name tag.
     *
     * @param prefix the prefix element to set.
     */
    public void setPrefix(NameTagElement<String, ?> prefix) {
        this.prefix = prefix;
    }

    /**
     * Retrieves the suffix element of the name tag.
     *
     * @return the suffix element of the name tag.
     */
    public NameTagElement<String, ?> getSuffix() {
        return suffix;
    }

    /**
     * Sets the suffix element of the name tag.
     *
     * @param suffix the suffix element to set.
     */
    public void setSuffix(NameTagElement<String, ?> suffix) {
        this.suffix = suffix;
    }

    /**
     * Retrieves the text to be displayed below the player's name.
     *
     * @return the below-name text element.
     */
    public NameTagElement<String, ?> getBelowName() {
        return belowName;
    }

    /**
     * Sets the text to be displayed below the player's name.
     *
     * @param belowName the below-name text element to set.
     */
    public void setBelowName(NameTagElement<String, ?> belowName) {
        this.belowName = belowName;
    }

    /**
     * Retrieves the value to be displayed below the player's name.
     *
     * @return the below-name value element.
     */
    public NameTagElement<Integer, ?> getBelowNameValue() {
        return belowNameValue;
    }

    /**
     * Sets the value to be displayed below the player's name.
     *
     * @param belowNameValue the below-name value element to set.
     */
    public void setBelowNameValue(NameTagElement<Integer, ?> belowNameValue) {
        this.belowNameValue = belowNameValue;
    }

    /**
     * Retrieves the text to be displayed above the player's name.
     *
     * @return the above-name text element.
     */
    public NameTagElement<String, ?> getAboveName() {
        return aboveName;
    }

    /**
     * Sets the text to be displayed above the player's name.
     *
     * @param aboveName the above-name text element to set.
     */
    public void setAboveName(NameTagElement<String, ?> aboveName) {
        this.aboveName = aboveName;
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
    public void assertReady(Player player) {
        if (this.player == null) {
            throw new VisualNotReadyException("The name tag's player was not set!");
        }
        if (prefix == null) {
            throw new VisualNotReadyException("The name tag's prefix was not set!");
        }
        if (suffix == null) {
            throw new VisualNotReadyException("The name tag's suffix was not set!");
        }
        if (belowNameValue != null && belowName == null) {
            throw new VisualNotReadyException("The below name value was set while below name display wasn't set!");
        }
        if (belowName != null && belowNameValue == null) {
            throw new VisualNotReadyException("The below name display was set while below name value wasn't set!");
        }
    }
}

