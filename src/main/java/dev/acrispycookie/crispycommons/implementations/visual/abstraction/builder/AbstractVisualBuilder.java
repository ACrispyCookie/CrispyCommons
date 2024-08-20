package dev.acrispycookie.crispycommons.implementations.visual.abstraction.builder;

import dev.acrispycookie.crispycommons.api.visual.abstraction.builder.VisualBuilder;
import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * An abstract base class for building visual elements in the CrispyCommons plugin.
 * <p>
 * The {@code AbstractVisualBuilder} class provides a template for constructing visual elements
 * by implementing the {@link VisualBuilder} interface. It allows for the configuration of common
 * properties such as visibility, players, and time-to-live settings.
 * </p>
 *
 * @param <T> the type of visual element that this builder will construct.
 */
public abstract class AbstractVisualBuilder<T extends CrispyVisual> implements VisualBuilder<T> {

    /**
     * The visual element being constructed.
     */
    protected T toBuild;

    /**
     * Indicates whether the visual element should be publicly visible to all players.
     */
    protected boolean isPublic = false;

    /**
     * A set of players who will receive the visual element.
     */
    protected final Set<OfflinePlayer> receivers = new HashSet<>();

    /**
     * The time-to-live (TTL) setting for the visual element, controlling its lifespan.
     */
    protected TimeToLiveElement<?> timeToLive = TimeToLiveElement.simple(-1L, TimeToLiveElement.StartMode.GLOBAL);

    /**
     * Adds a player to the set of receivers for this visual element.
     *
     * @param p the player to add.
     * @return the builder instance for chaining.
     */
    @Override
    public AbstractVisualBuilder<T> addPlayer(OfflinePlayer p) {
        receivers.add(p);
        return this;
    }

    /**
     * Removes a player from the set of receivers for this visual element.
     *
     * @param p the player to remove.
     * @return the builder instance for chaining.
     */
    @Override
    public AbstractVisualBuilder<T> removePlayer(OfflinePlayer p) {
        receivers.remove(p);
        return this;
    }

    /**
     * Sets the players who will receive this visual element, replacing any existing receivers.
     *
     * @param p the collection of players to set as receivers.
     * @return the builder instance for chaining.
     */
    @Override
    public AbstractVisualBuilder<T> setPlayers(Collection<? extends OfflinePlayer> p) {
        receivers.clear();
        receivers.addAll(p);
        return this;
    }

    /**
     * Adds a collection of players to the set of receivers for this visual element.
     *
     * @param p the collection of players to add.
     * @return the builder instance for chaining.
     */
    @Override
    public AbstractVisualBuilder<T> addPlayers(Collection<? extends OfflinePlayer> p) {
        receivers.addAll(p);
        return this;
    }

    /**
     * Sets whether the visual element should be publicly visible to all players.
     *
     * @param isPublic {@code true} if the visual element should be public, {@code false} otherwise.
     * @return the builder instance for chaining.
     */
    @Override
    public AbstractVisualBuilder<T> setPublic(boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    /**
     * Sets the time-to-live (TTL) for the visual element, controlling its lifespan.
     *
     * @param timeToLive the {@link TimeToLiveElement} that defines the TTL settings.
     * @return the builder instance for chaining.
     */
    @Override
    public AbstractVisualBuilder<T> setTimeToLive(TimeToLiveElement<?> timeToLive) {
        this.timeToLive = timeToLive;
        return this;
    }
}

