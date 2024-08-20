package dev.acrispycookie.crispycommons.implementations.visual.particle;

import dev.acrispycookie.crispycommons.implementations.visual.particle.data.ParticleData;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import dev.acrispycookie.crispycommons.implementations.particle.RenderedEffect;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * A concrete implementation of {@link AbstractParticle} that represents a rendered particle effect.
 * <p>
 * {@code RenderedParticle} handles the display of complex, custom-rendered particle effects on a per-player basis.
 * This class is designed to render a specific type of particle effect at a location determined by the data context of each player.
 * </p>
 */
public class RenderedParticle extends AbstractParticle<RenderedEffect> {

    /**
     * Constructs a {@code RenderedParticle} with the specified data, receivers, time-to-live, and visibility settings.
     *
     * @param data       the {@link ParticleData} that contains the visual and functional data for the rendered particle effect.
     * @param receivers  the set of players who will see the rendered particle effect.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the rendered particle effect.
     * @param isPublic   whether the rendered particle effect should be visible to all players.
     */
    public RenderedParticle(ParticleData<RenderedEffect> data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER, isPublic);
    }

    /**
     * Displays the rendered particle effect to the specified player.
     * <p>
     * This method is intended to retrieve the appropriate rendered particle effect and location for the player from the particle's data context
     * and render the effect. The implementation of the rendering logic should be added here. TODO
     * </p>
     *
     * @param player the player to whom the rendered particle effect will be shown.
     */
    @Override
    protected void show(Player player) {
        // Implementation of custom rendering logic should be added here.
    }

    /**
     * Hides the rendered particle effect from the specified player.
     * <p>
     * This method is currently empty, as no specific action is needed to hide the rendered particle effect.
     * </p>
     *
     * @param player the player from whom the rendered particle effect will be hidden.
     */
    @Override
    protected void hide(Player player) {
        // No specific action needed to hide the rendered particle effect.
    }

    /**
     * Updates the rendered particle effect for a specific player.
     * <p>
     * This method re-shows the rendered particle effect to the player, effectively updating its content.
     * </p>
     *
     * @param player the player for whom the rendered particle effect will be updated.
     */
    @Override
    protected void perPlayerUpdate(Player player) {
        show(player);
    }

    /**
     * Updates the rendered particle effect globally.
     * <p>
     * This method is empty because global updates are not used in this implementation.
     * </p>
     */
    @Override
    protected void globalUpdate() {
        // No global update action needed.
    }
}

