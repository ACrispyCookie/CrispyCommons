package dev.acrispycookie.crispycommons.implementations.visual.abstraction.visual;

import dev.acrispycookie.crispycommons.SpigotCrispyCommons;
import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import dev.acrispycookie.crispycommons.logging.CrispyLogger;
import dev.acrispycookie.crispycommons.utility.visual.TimeToLiveManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * An abstract base class that provides a framework for creating visual elements in the game, such as action bars, titles, and holograms.
 * <p>
 * The {@code AbstractVisual} class implements the {@link CrispyVisual} interface and provides core functionality for displaying,
 * hiding, updating, and managing the lifecycle of visual elements. It handles player-specific display logic, time-to-live management,
 * and updates through the Bukkit event system.
 * </p>
 *
 * @param <T> the type of the visual data that this visual element uses.
 */
public abstract class AbstractVisual<T extends VisualData> implements CrispyVisual, Listener {

    /**
     * A set of UUIDs representing players who are intended to receive this visual.
     */
    private final Set<UUID> receivers = new HashSet<>();

    /**
     * A set of UUIDs representing players who are currently viewing this visual.
     */
    private final Set<UUID> currentlyDisplaying = new HashSet<>();

    /**
     * Manages the time-to-live (TTL) for this visual element, controlling when it should expire.
     */
    protected TimeToLiveManager timeToLive;

    /**
     * The mode that dictates how updates to the visual should be handled.
     */
    private final UpdateMode updateMode;

    /**
     * Indicates whether this visual is publicly visible to all players.
     */
    protected final boolean isPublic;

    /**
     * Tracks whether this visual is currently being displayed.
     */
    protected boolean isDisplayed = false;

    /**
     * Tracks whether this visual has been destroyed and is no longer usable.
     */
    protected boolean isDestroyed = false;

    /**
     * The data associated with this visual element.
     */
    protected T data;

    /**
     * Prepares the visual to be shown, typically called before the first player views the visual.
     */
    protected abstract void prepareShow();

    /**
     * Prepares the visual to be hidden, typically called after the last player stops viewing the visual.
     */
    protected abstract void prepareHide();

    /**
     * Shows the visual to the specified player.
     *
     * @param player the player to show the visual to.
     */
    protected abstract void show(@NotNull Player player);

    /**
     * Hides the visual from the specified player.
     *
     * @param player the player to hide the visual from.
     */
    protected abstract void hide(@NotNull Player player);

    /**
     * Performs a global update of the visual, affecting all players who are viewing it.
     */
    protected abstract void globalUpdate();

    /**
     * Performs a player-specific update of the visual.
     *
     * @param player the player whose visual should be updated.
     */
    protected abstract void perPlayerUpdate(@NotNull Player player);

    /**
     * Constructs an {@code AbstractVisual} with the specified parameters.
     *
     * @param data      the visual data used by this visual element.
     * @param receivers the initial set of players who should receive this visual.
     * @param timeToLive the time-to-live element that controls the lifespan of this visual.
     * @param mode      the mode that dictates how updates should be handled.
     * @param isPublic  whether this visual is public and should be shown to all online players.
     */
    public AbstractVisual(@NotNull T data, @NotNull Set<? extends OfflinePlayer> receivers, @NotNull TimeToLiveElement<?> timeToLive, @NotNull UpdateMode mode, boolean isPublic) {
        this.data = data;
        this.updateMode = mode;
        this.isPublic = isPublic;
        if (this.isPublic)
            this.receivers.addAll(Bukkit.getOnlinePlayers().stream().map(Player::getUniqueId).collect(Collectors.toSet()));
        else
            this.receivers.addAll(receivers.stream().map(OfflinePlayer::getUniqueId).collect(Collectors.toSet()));
        this.timeToLive = new TimeToLiveManager(timeToLive);
        Bukkit.getPluginManager().registerEvents(this, SpigotCrispyCommons.getInstance().getBukkitPlugin());
    }

    /**
     * Handles the event where a player joins the server.
     * If the visual is public, the player is automatically added to the receivers list.
     * If the visual is displayed and the player is a receiver, it is shown to the player.
     *
     * @param event the player join event.
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        if (isPublic) {
            addPlayer(event.getPlayer());
            return;
        }
        if (!isDisplayed)
            return;
        if (receivers.contains(event.getPlayer().getUniqueId()))
            showInternal(event.getPlayer());
    }

    /**
     * Handles the event where a player leaves the server.
     * If the visual is public, the player is automatically removed from the receivers list.
     * If the visual is displayed and the player is currently viewing it, it is hidden from the player.
     *
     * @param event the player quit event.
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onLeave(PlayerQuitEvent event) {
        if (isPublic) {
            removePlayer(event.getPlayer());
            return;
        }
        if (!isDisplayed)
            return;
        if (receivers.contains(event.getPlayer().getUniqueId()) && currentlyDisplaying.contains(event.getPlayer().getUniqueId()))
            hideInternal(event.getPlayer());
    }

    /**
     * Shows the visual to a player internally, handling preparation and time-to-live setup.
     *
     * @param player the player to show the visual to.
     */
    protected void showInternal(@NotNull Player player) {
        if (isExpired(player) && currentlyDisplaying.contains(player.getUniqueId()))
            return;
        if (currentlyDisplaying.isEmpty())
            prepareShow();
        currentlyDisplaying.add(player.getUniqueId());
        show(player);
        timeToLive.setupPerPlayer(() -> hideInternal(player), player);
    }

    /**
     * Hides the visual from a player internally, handling cleanup and time-to-live cancellation.
     *
     * @param player the player to hide the visual from.
     */
    protected void hideInternal(@NotNull Player player) {
        if (!currentlyDisplaying.contains(player.getUniqueId()))
            return;
        currentlyDisplaying.remove(player.getUniqueId());
        if (currentlyDisplaying.isEmpty()) {
            prepareHide();
        }
        hide(player);
        timeToLive.cancelPlayer(player);
    }

    @Override
    public void show() {
        if (isDisplayed || isDestroyed) return;
        isDisplayed = true;

        receivers.stream().map(Bukkit::getOfflinePlayer).filter(offlinePlayer -> offlinePlayer.getPlayer() != null).forEach(offlinePlayer -> {
            try {
                data.assertReady(offlinePlayer.getPlayer());
                showInternal(offlinePlayer.getPlayer());
            } catch (VisualData.VisualNotReadyException e) {
                CrispyLogger.printException(SpigotCrispyCommons.getInstance().getPlugin(), e, "This visual couldn't be displayed to the player: " + offlinePlayer.getName());
            }
        });

        timeToLive.setupGlobal(this::hide, (offlinePlayer) -> this.hideInternal((Player) offlinePlayer), currentlyDisplaying.stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toSet()));
    }

    @Override
    public void hide() {
        if (!isDisplayed || isDestroyed) return;
        isDisplayed = false;

        receivers.stream().map(Bukkit::getOfflinePlayer).filter(offlinePlayer -> offlinePlayer.getPlayer() != null).forEach(offlinePlayer -> hideInternal(offlinePlayer.getPlayer()));

        timeToLive.cancelGlobal();
    }

    @Override
    public void update() {
        if (!isDisplayed || isDestroyed) return;

        if (updateMode == UpdateMode.BOTH) {
            globalUpdate();
            new HashSet<>(currentlyDisplaying).stream().map(Bukkit::getOfflinePlayer).filter(offlinePlayer -> offlinePlayer.getPlayer() != null).forEach(offlinePlayer -> perPlayerUpdate(offlinePlayer.getPlayer()));
        } else if (updateMode == UpdateMode.GLOBAL) {
            globalUpdate();
        } else if (updateMode == UpdateMode.PER_PLAYER) {
            new HashSet<>(currentlyDisplaying).stream().map(Bukkit::getOfflinePlayer).filter(offlinePlayer -> offlinePlayer.getPlayer() != null).forEach(offlinePlayer -> perPlayerUpdate(offlinePlayer.getPlayer()));
        }
    }

    public void destroy() {
        if (isDestroyed)
            return;
        if (isDisplayed)
            hide();
        receivers.clear();
        currentlyDisplaying.clear();
        data = null;
        timeToLive = null;
        isDestroyed = true;
        isDisplayed = false;
        HandlerList.unregisterAll(this);
    }

    @Override
    public void addPlayer(@NotNull OfflinePlayer player) {
        if (receivers.contains(player.getUniqueId()) || isDestroyed) return;

        receivers.add(player.getUniqueId());
        if (player.getPlayer() != null && isDisplayed)
            showInternal(player.getPlayer());
    }

    @Override
    public void removePlayer(@NotNull OfflinePlayer player) {
        if (!receivers.contains(player.getUniqueId()) || isDestroyed) return;

        if (player.getPlayer() != null && isDisplayed)
            hideInternal(player.getPlayer());
        receivers.remove(player.getUniqueId());
    }

    @Override
    public void setPlayers(@NotNull Collection<? extends OfflinePlayer> players) {
        if (isDestroyed)
            return;
        Set<OfflinePlayer> toRemove = receivers.stream().map(Bukkit::getOfflinePlayer).filter(offlinePlayer -> !players.contains(offlinePlayer)).collect(Collectors.toSet());
        Set<OfflinePlayer> toAdd = players.stream().filter(offlinePlayer -> !receivers.contains(offlinePlayer.getUniqueId())).collect(Collectors.toSet());
        toRemove.forEach(this::removePlayer);
        toAdd.forEach(this::addPlayer);
    }

    @Override
    public void resetExpired() {
        if (isDestroyed)
            return;
        Set<OfflinePlayer> expired = timeToLive.getExpired();
        timeToLive.resetExpired((offlinePlayer) -> hideInternal((Player) offlinePlayer), currentlyDisplaying.stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toSet()));

        for (OfflinePlayer player : expired) {
            if (!player.isOnline())
                return;

            Player onlinePlayer = (Player) player;
            if (currentlyDisplaying.isEmpty())
                prepareShow();
            currentlyDisplaying.add(onlinePlayer.getUniqueId());
            show(onlinePlayer);
        }
    }

    @Override
    public void resetExpired(@NotNull Player player) {
        if (!isExpired(player) || isDestroyed)
            return;

        timeToLive.resetExpired(player.getUniqueId(), () -> hideInternal(player));

        if (currentlyDisplaying.isEmpty())
            prepareShow();
        currentlyDisplaying.add(player.getUniqueId());
        show(player);
    }

    @Override
    public @NotNull Set<OfflinePlayer> getPlayers() {
        return receivers.stream().map(Bukkit::getOfflinePlayer).collect(Collectors.toSet());
    }

    @Override
    public @NotNull Set<Player> getCurrentlyViewing() {
        return currentlyDisplaying.stream().map(Bukkit::getPlayer).collect(Collectors.toSet());
    }

    @Override
    public void setTimeToLive(@NotNull TimeToLiveElement<?> timeToLive) {
        this.timeToLive.setElement(timeToLive);
    }

    @Override
    public @NotNull TimeToLiveElement<?> getTimeToLive() {
        return timeToLive.getElement();
    }

    @Override
    public boolean isDisplayed() {
        return isDisplayed;
    }

    @Override
    public boolean isAnyoneWatching() {
        return isDisplayed && !currentlyDisplaying.isEmpty();
    }

    @Override
    public boolean isPublic() {
        return isPublic;
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    @Override
    public boolean isWatching(@NotNull Player player) {
        return isDisplayed && currentlyDisplaying.contains(player.getUniqueId());
    }

    @Override
    public boolean isExpired(@NotNull OfflinePlayer player) {
        return timeToLive.isExpired(player);
    }

    @Override
    public boolean isRunning(@NotNull Player player) {
        return timeToLive.isRunning(player);
    }

    /**
     * Enum representing the mode in which updates to the visual should be applied.
     */
    public enum UpdateMode {
        /**
         * Updates are applied per player.
         */
        PER_PLAYER,
        /**
         * Updates are applied globally to all players.
         */
        GLOBAL,
        /**
         * Updates are applied both per player and globally.
         */
        BOTH
    }
}

