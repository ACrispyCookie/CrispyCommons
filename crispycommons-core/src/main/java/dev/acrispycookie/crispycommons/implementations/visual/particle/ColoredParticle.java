package dev.acrispycookie.crispycommons.implementations.visual.particle;

import dev.acrispycookie.crispycommons.implementations.visual.particle.data.ParticleData;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import dev.acrispycookie.crispycommons.implementations.particle.ColoredEffect;
import dev.acrispycookie.crispycommons.nms.utility.ParticleSpawner;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

/**
 * A concrete implementation of {@link AbstractParticle} that represents a colored particle effect.
 * <p>
 * {@code ColoredParticle} handles the display of colored particle effects on a per-player basis.
 * It is designed to render a specific colored particle effect at a location determined by the data context of each player.
 * </p>
 */
public class ColoredParticle extends AbstractParticle<ColoredEffect> {

    /**
     * Constructs a {@code ColoredParticle} with the specified data, receivers, time-to-live, and visibility settings.
     *
     * @param data       the {@link ParticleData} that contains the visual and functional data for the colored particle effect.
     * @param receivers  the set of players who will see the colored particle effect.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the colored particle effect.
     * @param isPublic   whether the colored particle effect should be visible to all players.
     */
    public ColoredParticle(ParticleData<ColoredEffect> data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER, isPublic);
    }

    /**
     * Displays the colored particle effect to the specified player.
     * <p>
     * This method retrieves the appropriate colored particle effect and location for the player from the particle's data context
     * and plays the effect at the specified location with the specified color.
     * </p>
     *
     * @param player the player to whom the colored particle effect will be shown.
     */
    @Override
    protected void show(Player player) {
        ColoredEffect effect = data.getElement().getFromContext(OfflinePlayer.class, player);
        Location location = data.getLocation().getFromContext(OfflinePlayer.class, player);
        ParticleSpawner.newInstance().spawnColored(player, effect.getEffect(), location, effect.getNormalisedRed(), effect.getNormalisedGreen(), effect.getNormalisedBlue());
    }

    /**
     * Hides the colored particle effect from the specified player.
     * <p>
     * This method is currently empty, as no specific action is needed to hide the colored particle effect.
     * </p>
     *
     * @param player the player from whom the colored particle effect will be hidden.
     */
    @Override
    protected void hide(Player player) {
        // No specific action needed to hide the colored particle effect.
    }

    /**
     * Updates the colored particle effect for a specific player.
     * <p>
     * This method re-shows the colored particle effect to the player, effectively updating its content.
     * </p>
     *
     * @param player the player for whom the colored particle effect will be updated.
     */
    @Override
    protected void perPlayerUpdate(Player player) {
        show(player);
    }

    /**
     * Updates the colored particle effect globally.
     * <p>
     * This method is empty because global updates are not used in this implementation.
     * </p>
     */
    @Override
    protected void globalUpdate() {
        // No global update action needed.
    }
}

