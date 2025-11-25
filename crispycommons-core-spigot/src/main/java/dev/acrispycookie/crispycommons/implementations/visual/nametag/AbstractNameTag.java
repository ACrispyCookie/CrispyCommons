package dev.acrispycookie.crispycommons.implementations.visual.nametag;

import dev.acrispycookie.crispycommons.api.visual.hologram.CrispyHologram;
import dev.acrispycookie.crispycommons.api.visual.nametag.CrispyNameTag;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visual.nametag.data.NameTagData;
import dev.acrispycookie.crispycommons.implementations.element.type.NameTagElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import dev.acrispycookie.crispycommons.utility.visual.FieldUpdaterHelper;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * An abstract base class that provides the core functionality for a name tag visual element.
 * <p>
 * {@code AbstractNameTag} manages the display of name tag elements such as prefix, suffix,
 * and additional text above or below the player's name. It extends the {@link AbstractVisual}
 * class and implements the {@link CrispyNameTag} interface.
 * </p>
 */
public abstract class AbstractNameTag extends AbstractVisual<NameTagData> implements CrispyNameTag {

    /**
     * An optional hologram that can be displayed above the player's name tag.
     */
    protected CrispyHologram aboveNameHologram;

    /**
     * Constructs a new {@code AbstractNameTag} instance with the specified data, receivers, time-to-live,
     * update mode, and visibility.
     *
     * @param data       the {@link NameTagData} containing the data for the name tag elements.
     * @param receivers  the set of players who will receive the name tag.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the name tag.
     * @param updateMode the mode of updating the name tag, whether globally, per player, or both.
     * @param isPublic   whether the name tag should be visible to all players.
     */
    AbstractNameTag(@NotNull NameTagData data, @NotNull Set<? extends OfflinePlayer> receivers, @NotNull TimeToLiveElement<?> timeToLive, @NotNull UpdateMode updateMode, boolean isPublic) {
        super(data, receivers, timeToLive, updateMode, isPublic);
    }

    /**
     * Handles the event where a player dies. If the player is the one associated with this name tag,
     * the above-name hologram (if present) will be hidden.
     *
     * @param event the {@link PlayerDeathEvent} triggered when a player dies.
     */
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity().equals(data.getPlayer())) {
            if (isAnyoneWatching() && aboveNameHologram != null)
                aboveNameHologram.hide();
        }
    }

    /**
     * Handles the event where a player respawns. If the player is the one associated with this name tag,
     * the above-name hologram (if present) will be shown again.
     *
     * @param event the {@link PlayerRespawnEvent} triggered when a player respawns.
     */
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer().equals(data.getPlayer())) {
            if (isAnyoneWatching() && aboveNameHologram != null)
                aboveNameHologram.show();
        }
    }

    /**
     * Prepares the name tag elements for display by starting their updates.
     * <p>
     * This method is called before the name tag is shown to ensure all dynamic elements,
     * such as prefix, suffix, and other name tag texts, are properly initialized.
     * </p>
     */
    @Override
    protected void prepareShow() {
        if (data.getAboveName() != null)
            data.getAboveName().start();
        if (data.getBelowName() != null)
            data.getBelowName().start();
        if (data.getBelowNameValue() != null)
            data.getBelowNameValue().start();
        if (data.getPrefix() != null)
            data.getPrefix().start();
        if (data.getSuffix() != null)
            data.getSuffix().start();
    }

    /**
     * Prepares the name tag elements for hiding by stopping their updates.
     * <p>
     * This method is called before the name tag is hidden to ensure all dynamic elements,
     * such as prefix, suffix, and other name tag texts, are properly stopped. It also
     * destroys the above-name hologram if it exists.
     * </p>
     */
    @Override
    protected void prepareHide() {
        if (aboveNameHologram != null) {
            aboveNameHologram.destroy();
            aboveNameHologram = null;
        }
        if (data.getAboveName() != null)
            data.getAboveName().stop();
        if (data.getBelowName() != null)
            data.getBelowName().stop();
        if (data.getBelowNameValue() != null)
            data.getBelowNameValue().stop();
        if (data.getPrefix() != null)
            data.getPrefix().stop();
        if (data.getSuffix() != null)
            data.getSuffix().stop();
    }

    /**
     * Sets the prefix text element for the name tag.
     * <p>
     * This method stops the current prefix element, sets the new one, and restarts it if
     * the name tag is currently being displayed.
     * </p>
     *
     * @param prefix the new prefix {@link NameTagElement} to set.
     */
    @Override
    public void setPrefix(@NotNull NameTagElement<String, ?> prefix) {
        FieldUpdaterHelper.setNormalField(prefix, data::getPrefix, data::setPrefix, isAnyoneWatching(), this::updatePrefix);
    }

    /**
     * Sets the suffix text element for the name tag.
     * <p>
     * This method stops the current suffix element, sets the new one, and restarts it if
     * the name tag is currently being displayed.
     * </p>
     *
     * @param suffix the new suffix {@link NameTagElement} to set.
     */
    @Override
    public void setSuffix(@NotNull NameTagElement<String, ?> suffix) {
        FieldUpdaterHelper.setNormalField(suffix, data::getSuffix, data::setSuffix, isAnyoneWatching(), this::updateSuffix);
    }

    /**
     * Sets the below-name text element for the name tag.
     * <p>
     * This method stops the current below-name element, sets the new one, and restarts it if
     * the name tag is currently being displayed.
     * </p>
     *
     * @param belowName the new below-name {@link NameTagElement} to set.
     */
    @Override
    public void setBelowName(@NotNull NameTagElement<String, ?> belowName) {
        FieldUpdaterHelper.setNormalField(belowName, data::getBelowName, data::setBelowName, isAnyoneWatching(), this::updateBelowName);
    }

    /**
     * Sets the below-name value element for the name tag.
     * <p>
     * This method stops the current below-name value element, sets the new one, and restarts it if
     * the name tag is currently being displayed.
     * </p>
     *
     * @param belowNameValue the new below-name value {@link NameTagElement} to set.
     */
    @Override
    public void setBelowNameValue(@NotNull NameTagElement<Integer, ?> belowNameValue) {
        FieldUpdaterHelper.setNormalField(belowNameValue, data::getBelowNameValue, data::setBelowNameValue, isAnyoneWatching(), this::updateBelowNameValue);
    }

    /**
     * Sets the above-name text element for the name tag.
     * <p>
     * This method stops the current above-name element, sets the new one, and restarts it if
     * the name tag is currently being displayed.
     * </p>
     *
     * @param aboveName the new above-name {@link NameTagElement} to set.
     */
    @Override
    public void setAboveName(@NotNull NameTagElement<String, ?> aboveName) {
        FieldUpdaterHelper.setNormalField(aboveName, data::getAboveName, data::setAboveName, isAnyoneWatching(), this::updateAboveName);
    }

    /**
     * Retrieves the prefix text element of the name tag.
     *
     * @return the prefix {@link NameTagElement}.
     */
    @Override
    public @Nullable NameTagElement<String, ?> getPrefix() {
        return data.getPrefix() != null ? data.getPrefix().getElement() : null;
    }

    /**
     * Retrieves the suffix text element of the name tag.
     *
     * @return the suffix {@link NameTagElement}.
     */
    @Override
    public @Nullable NameTagElement<String, ?> getSuffix() {
        return data.getSuffix() != null ? data.getSuffix().getElement() : null;
    }

    /**
     * Retrieves the below-name text element of the name tag.
     *
     * @return the below-name {@link NameTagElement}.
     */
    @Override
    public @Nullable NameTagElement<String, ?> getBelowName() {
        return data.getBelowName() != null ? data.getBelowName().getElement() : null;
    }

    /**
     * Retrieves the below-name value element of the name tag.
     *
     * @return the below-name value {@link NameTagElement}.
     */
    @Override
    public @Nullable NameTagElement<Integer, ?> getBelowNameValue() {
        return data.getBelowNameValue() != null ? data.getBelowNameValue().getElement() : null;
    }

    /**
     * Retrieves the above-name text element of the name tag.
     *
     * @return the above-name {@link NameTagElement}.
     */
    @Override
    public @Nullable NameTagElement<String, ?> getAboveName() {
        return data.getAboveName() != null ? data.getAboveName().getElement() : null;
    }
}

