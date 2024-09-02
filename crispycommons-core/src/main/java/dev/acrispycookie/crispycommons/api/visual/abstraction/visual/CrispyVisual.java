package dev.acrispycookie.crispycommons.api.visual.abstraction.visual;

import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Set;

/**
 * Represents a visual element that can be shown, hidden, updated, and destroyed within the CrispyCommons framework.
 * <p>
 * A {@code CrispyVisual} can manage its visibility, lifecycle, and the players who can see it. It also supports
 * time-to-live (TTL) management and allows for customization of player-specific viewing experiences.
 * </p>
 */
public interface CrispyVisual {

    /**
     * Displays the visual element, for all the current viewers.
     */
    void show();

    /**
     * Hides the visual element, for all the current viewers.
     */
    void hide();

    /**
     * Updates the visual element, typically reflecting any changes in its state.
     */
    void update();

    /**
     * Destroys the visual element, removing it from view and preventing further updates.
     */
    void destroy();

    /**
     * Sets the time-to-live (TTL) for the visual element, determining how long it should be visible.
     *
     * @param timeToLive the {@link TimeToLiveElement} managing the TTL.
     */
    void setTimeToLive(@NotNull TimeToLiveElement<?> timeToLive);

    /**
     * Adds a player to the list of players who can view this visual element.
     *
     * @param player the {@link OfflinePlayer} to be added.
     */
    void addPlayer(@NotNull OfflinePlayer player);

    /**
     * Removes a player from the list of players who can view this visual element.
     *
     * @param player the {@link OfflinePlayer} to be removed.
     */
    void removePlayer(@NotNull OfflinePlayer player);

    /**
     * Sets the players who can view this visual element.
     *
     * @param players a collection of {@link OfflinePlayer} instances representing the players.
     */
    void setPlayers(@NotNull Collection<? extends OfflinePlayer> players);

    /**
     * Resets the expired status of all players, allowing them to view the visual element again if applicable.
     */
    void resetExpired();

    /**
     * Resets the expired status for a specific player, allowing them to view the visual element again.
     *
     * @param player the {@link Player} whose expired status should be reset.
     */
    void resetExpired(@NotNull Player player);

    /**
     * Retrieves the set of players who can view this visual element.
     *
     * @return a set of {@link OfflinePlayer} instances.
     */
    @NotNull Set<OfflinePlayer> getPlayers();

    /**
     * Retrieves the set of players who are currently viewing this visual element.
     *
     * @return a set of {@link Player} instances.
     */
    @NotNull Set<Player> getCurrentlyViewing();

    /**
     * Retrieves the time-to-live (TTL) element associated with this visual.
     *
     * @return the {@link TimeToLiveElement} managing the TTL.
     */
    @NotNull TimeToLiveElement<?> getTimeToLive();

    /**
     * Checks if this visual element is public, meaning it is visible to all players.
     *
     * @return {@code true} if the visual element is public; {@code false} otherwise.
     */
    boolean isPublic();

    /**
     * Checks if this visual element is currently displayed.
     *
     * @return {@code true} if the visual element is displayed; {@code false} otherwise.
     */
    boolean isDisplayed();

    /**
     * Checks if this visual element has been destroyed.
     *
     * @return {@code true} if the visual element is destroyed; {@code false} otherwise.
     */
    boolean isDestroyed();

    /**
     * Checks if there are any players currently watching this visual element.
     *
     * @return {@code true} if at least one player is watching; {@code false} otherwise.
     */
    boolean isAnyoneWatching();

    /**
     * Checks if a specific player is currently watching this visual element.
     *
     * @param player the {@link Player} to check.
     * @return {@code true} if the player is watching; {@code false} otherwise.
     */
    boolean isWatching(@NotNull Player player);

    /**
     * Checks if the visual element has expired for a specific player.
     *
     * @param player the {@link OfflinePlayer} to check.
     * @return {@code true} if the visual element has expired for the player; {@code false} otherwise.
     */
    boolean isExpired(@NotNull OfflinePlayer player);

    /**
     * Checks if the visual element's TTL is currently running for a specific player.
     *
     * @param player the {@link Player} to check.
     * @return {@code true} if the TTL is running for the player; {@code false} otherwise.
     */
    boolean isRunning(@NotNull Player player);
}

