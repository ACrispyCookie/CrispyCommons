package dev.acrispycookie.crispycommons.implementations.visual.hologram;

import dev.acrispycookie.crispycommons.SpigotCrispyCommons;
import dev.acrispycookie.crispycommons.api.visual.hologram.CrispyHologram;
import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import dev.acrispycookie.crispycommons.implementations.element.OwnedElement;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visual.hologram.data.HologramData;
import dev.acrispycookie.crispycommons.implementations.element.type.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import dev.acrispycookie.crispycommons.utility.visual.FieldUpdaterHelper;
import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.Version;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An abstract class that represents a hologram in the CrispyCommons framework.
 * <p>
 * {@code AbstractHologram} extends {@link AbstractVisual} and implements {@link CrispyHologram},
 * providing the core functionality needed to manage holograms, including adding, removing, and updating lines,
 * as well as managing the hologram's location.
 * </p>
 */
public abstract class AbstractHologram extends AbstractVisual<HologramData> implements CrispyHologram {

    /**
     * Updates all the lines in the hologram.
     * <p>
     * This method is abstract and must be implemented by subclasses to define
     * how the lines in the hologram should be updated.
     * </p>
     */
    protected abstract void resetLines();

    /**
     * Removes a line from the hologram at the specified index.
     * <p>
     * This method is abstract and must be implemented by subclasses to define
     * how a line should be removed internally within the hologram.
     * </p>
     *
     * @param index the index of the line to be removed.
     */
    protected abstract void removeLineInternal(int index);

    /**
     * Adds a line to the hologram at the specified index.
     * <p>
     * This method is abstract and must be implemented by subclasses to define
     * how a line should be added internally within the hologram.
     * </p>
     *
     * @param index the index at which the line should be added.
     */
    protected abstract void addLineInternal(int index);

    /**
     * Constructs an {@code AbstractHologram} with the specified data, receivers, time-to-live, update mode, and visibility.
     *
     * @param data       the {@link HologramData} containing the data for the hologram.
     * @param receivers  the set of players who will receive the hologram.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the hologram.
     * @param updateMode the {@link UpdateMode} that defines how the hologram should be updated.
     * @param isPublic   whether the hologram should be visible to all players.
     */
    AbstractHologram(@NotNull HologramData data, @NotNull Set<? extends OfflinePlayer> receivers, @NotNull TimeToLiveElement<?> timeToLive, @NotNull UpdateMode updateMode, boolean isPublic) {
        super(data, receivers, timeToLive, updateMode, isPublic);
    }

    /**
     * Handles the event when a player dies.
     * <p>
     * If the player is one of the hologram's receivers and the hologram is currently displayed,
     * the hologram is hidden from the player.
     * </p>
     *
     * @param event the {@link PlayerDeathEvent} triggered when a player dies.
     */
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (VersionManager.getVersion().isHigher(Version.v1_8_R3))
            return;
        if (isWatching(event.getEntity())) {
            hideInternal(event.getEntity());
        }
    }

    /**
     * Handles the event when a player respawns.
     * <p>
     * If the player is one of the hologram's receivers and the hologram is currently displayed,
     * the hologram is shown to the player again after a short delay.
     * </p>
     *
     * @param event the {@link PlayerRespawnEvent} triggered when a player respawns.
     */
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (VersionManager.getVersion().isHigher(Version.v1_8_R3))
            return;
        if (getPlayers().contains(event.getPlayer()) && !isExpired(event.getPlayer()) && isDisplayed) {
            Bukkit.getScheduler().runTaskLater(SpigotCrispyCommons.getInstance().getBukkitPlugin(), () -> showInternal(event.getPlayer()), 1L);
        }
    }

    /**
     * Handles the event when a player changes worlds.
     * <p>
     * If the player is one of the hologram's receivers and the hologram is currently displayed,
     * the hologram is shown to the player again after a short delay.
     * </p>
     *
     * @param event the {@link PlayerChangedWorldEvent} triggered when a player changes world.
     */
    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        GeneralElement<Location, ?> locationGeneralElement = getLocation();
        if (locationGeneralElement == null || locationGeneralElement.isDynamic())
            return;

        Location location = locationGeneralElement.getFromContext(OfflinePlayer.class, player);
        assert location.getWorld() != null : "Location world is null. Contact developer.";
        if (getPlayers().contains(player) && isDisplayed) {
            if (location.getWorld().equals(event.getFrom()))
                Bukkit.getScheduler().runTaskLater(SpigotCrispyCommons.getInstance().getBukkitPlugin(), () -> hideInternal(event.getPlayer()), 1L);
            else if (location.getWorld().equals(player.getWorld()))
                Bukkit.getScheduler().runTaskLater(SpigotCrispyCommons.getInstance().getBukkitPlugin(), () -> showInternal(event.getPlayer()), 1L);
        }
    }

    /**
     * Prepares the hologram for display.
     * <p>
     * This method starts all dynamic elements and the location element in the hologram.
     * </p>
     */
    @Override
    protected void prepareShow() {
        data.getLines().forEach(OwnedElement::start);
        data.getLocation().start();
    }

    /**
     * Prepares the hologram for hiding.
     * <p>
     * This method stops all dynamic elements and the location element in the hologram.
     * </p>
     */
    @Override
    protected void prepareHide() {
        data.getLines().forEach(OwnedElement::stop);
        data.getLocation().stop();
    }

    /**
     * Adds a line to the hologram at the specified index.
     * <p>
     * If the hologram is currently displayed, the new line is also started.
     * </p>
     *
     * @param index the index at which to add the new line.
     * @param line  the {@link DynamicElement} to add as a line.
     */
    @Override
    public void addLine(int index, @NotNull DynamicElement<?, ?> line) {
        if (index > data.getLines().size()) {
            return;
        }

        data.addLine(index, line);
        data.getLines().get(index).setUpdate(() -> updateLine(index));
        addLineInternal(index);
        FieldUpdaterHelper.offsetAfterAdd(index, data.getLines(), isAnyoneWatching(), this::updateLine);
    }

    /**
     * Adds a line to the hologram at the end of the current list of lines.
     * <p>
     * If the hologram is currently displayed, the new line is also started.
     * </p>
     *
     * @param line the {@link DynamicElement} to add as a line.
     */
    @Override
    public void addLine(@NotNull DynamicElement<?, ?> line) {
        addLine(data.getLines().size(), line);
    }

    /**
     * Removes a line from the hologram at the specified index.
     * <p>
     * If the hologram is currently displayed, the line is also stopped.
     * </p>
     *
     * @param index the index of the line to remove.
     */
    @Override
    public void removeLine(int index) {
        if (index >= data.getLines().size()) {
            return;
        }

        data.removeLine(index).destroy();
        removeLineInternal(index);
        FieldUpdaterHelper.offsetAfterRemove(index, data.getLines(), isAnyoneWatching(), this::updateLine);
    }

    /**
     * Sets the lines to be displayed in the hologram.
     * <p>
     * If the hologram is currently displayed, all previous lines are stopped,
     * and the new lines are started.
     * </p>
     *
     * @param lines the collection of {@link DynamicElement} to set as the lines.
     */
    @Override
    public void setLines(@NotNull Collection<? extends DynamicElement<?, ?>> lines) {
        List<OwnedElement<DynamicElement<?, ?>>> oldLines = new ArrayList<>(data.getLines());
        data.setLines(new ArrayList<>(lines));
        resetLines();
        FieldUpdaterHelper.resetLines(oldLines, data.getLines(), isAnyoneWatching(), this::updateLine);
    }

    /**
     * Sets the location of the hologram.
     *
     * @param location the {@link GeneralElement} representing the new location.
     */
    @Override
    public void setLocation(@NotNull GeneralElement<Location, ?> location) {
        FieldUpdaterHelper.setNormalField(location, data::getLocation, data::setLocation, isAnyoneWatching(), this::updateLocation);
    }

    /**
     * Retrieves the location of the hologram.
     *
     * @return the {@link GeneralElement} representing the location.
     */
    @Override
    public @Nullable GeneralElement<Location, ?> getLocation() {
        return data.getLocation() != null ? data.getLocation().getElement() : null;
    }

    /**
     * Retrieves the lines currently displayed in the hologram.
     *
     * @return a list of {@link DynamicElement} representing the lines.
     */
    @Override
    public @NotNull List<DynamicElement<?, ?>> getLines() {
        return data.getLines().stream().map(OwnedElement::getElement).collect(Collectors.toList());
    }
}

