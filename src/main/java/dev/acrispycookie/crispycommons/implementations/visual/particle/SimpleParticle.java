package dev.acrispycookie.crispycommons.implementations.visual.particle;

import dev.acrispycookie.crispycommons.implementations.visual.particle.data.ParticleData;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import dev.acrispycookie.crispycommons.implementations.particle.SimpleEffect;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * A concrete implementation of {@link AbstractParticle} that represents a simple particle effect.
 * <p>
 * {@code SimpleParticle} handles the display of simple particle effects on a per-player basis.
 * It is designed to render a specific particle effect at a location determined by the data context of each player.
 * </p>
 */
public class SimpleParticle extends AbstractParticle<SimpleEffect> {

    /**
     * Constructs a {@code SimpleParticle} with the specified data, receivers, time-to-live, and visibility settings.
     *
     * @param data       the {@link ParticleData} that contains the visual and functional data for the particle effect.
     * @param receivers  the set of players who will see the particle effect.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the particle effect.
     * @param isPublic   whether the particle effect should be visible to all players.
     */
    public SimpleParticle(ParticleData<SimpleEffect> data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER, isPublic);
    }

    /**
     * Displays the particle effect to the specified player.
     * <p>
     * This method retrieves the appropriate particle effect and location for the player from the particle's data context
     * and plays the effect at the specified location.
     * </p>
     *
     * @param player the player to whom the particle effect will be shown.
     */
    @Override
    protected void show(Player player) {
        SimpleEffect effect = data.getElement().getFromContext(OfflinePlayer.class, player);
        Location location = data.getLocation().getFromContext(OfflinePlayer.class, player);
        player.spigot().playEffect(location, effect.getEffect(), effect.getData(), effect.getData(), 0, 0, 0, 1, 100, 160);
    }

    /**
     * Hides the particle effect from the specified player.
     * <p>
     * This method is currently empty, as no specific action is needed to hide the particle effect.
     * </p>
     *
     * @param player the player from whom the particle effect will be hidden.
     */
    @Override
    protected void hide(Player player) {
        // No specific action needed to hide the particle effect.
    }

    /**
     * Updates the particle effect for a specific player.
     * <p>
     * This method re-shows the particle effect to the player, effectively updating its content.
     * </p>
     *
     * @param player the player for whom the particle effect will be updated.
     */
    @Override
    protected void perPlayerUpdate(Player player) {
        show(player);
    }

    /**
     * Updates the particle effect globally.
     * <p>
     * This method is empty because global updates are not used in this implementation.
     * </p>
     */
    @Override
    protected void globalUpdate() {
        // No global update action needed.
    }
}

