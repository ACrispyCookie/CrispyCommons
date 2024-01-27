package dev.acrispycookie.crispycommons.implementations.visuals.particle;

import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.RenderedEffect;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public class RenderedParticle extends AbstractParticle<RenderedEffect> {

    public RenderedParticle(ParticleData<RenderedEffect> data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive);
    }

    @Override
    public void show(Player player) {
        // ???
    }

    @Override
    public void hide(Player player) {

    }

    @Override
    public void update(Player player) {
        show(player);
    }
}
