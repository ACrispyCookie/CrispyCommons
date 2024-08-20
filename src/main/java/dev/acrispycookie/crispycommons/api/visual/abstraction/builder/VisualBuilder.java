package dev.acrispycookie.crispycommons.api.visual.abstraction.builder;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import org.bukkit.OfflinePlayer;

import java.util.Collection;

/**
 * A builder interface for creating instances of {@link CrispyVisual}.
 * <p>
 * The {@code VisualBuilder} interface provides methods to configure various aspects of a visual element,
 * such as the players who can view it, its time-to-live, and whether it is public. The builder pattern allows
 * for a flexible and readable way to construct complex visual elements.
 * </p>
 *
 * @param <T> the type of {@link CrispyVisual} that this builder constructs.
 */
public interface VisualBuilder<T extends CrispyVisual> {

    /**
     * Adds a player to the list of players who can view the visual element.
     *
     * @param p the {@link OfflinePlayer} to add.
     * @return this {@code VisualBuilder} instance for method chaining.
     */
    VisualBuilder<T> addPlayer(OfflinePlayer p);

    /**
     * Removes a player from the list of players who can view the visual element.
     *
     * @param p the {@link OfflinePlayer} to remove.
     * @return this {@code VisualBuilder} instance for method chaining.
     */
    VisualBuilder<T> removePlayer(OfflinePlayer p);

    /**
     * Sets the list of players who can view the visual element, replacing any existing list.
     *
     * @param p a collection of {@link OfflinePlayer} objects to set.
     * @return this {@code VisualBuilder} instance for method chaining.
     */
    VisualBuilder<T> setPlayers(Collection<? extends OfflinePlayer> p);

    /**
     * Adds multiple players to the list of players who can view the visual element.
     *
     * @param p a collection of {@link OfflinePlayer} objects to add.
     * @return this {@code VisualBuilder} instance for method chaining.
     */
    VisualBuilder<T> addPlayers(Collection<? extends OfflinePlayer> p);

    /**
     * Sets the time-to-live (TTL) element for the visual element.
     * <p>
     * The TTL element determines how long the visual element will remain active.
     * </p>
     *
     * @param timeToLive the {@link TimeToLiveElement} that defines the TTL for the visual element.
     * @return this {@code VisualBuilder} instance for method chaining.
     */
    VisualBuilder<T> setTimeToLive(TimeToLiveElement<?> timeToLive);

    /**
     * Sets whether the visual element is public.
     * <p>
     * A public visual element is visible to all players by default.
     * </p>
     *
     * @param isPublic {@code true} if the visual element should be public; {@code false} otherwise.
     * @return this {@code VisualBuilder} instance for method chaining.
     */
    VisualBuilder<T> setPublic(boolean isPublic);

    /**
     * Builds and returns the configured {@link CrispyVisual} instance.
     *
     * @return the constructed {@link CrispyVisual} instance.
     */
    T build();
}

