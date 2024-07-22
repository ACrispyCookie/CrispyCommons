package dev.acrispycookie.crispycommons.implementations.visuals.particle;

import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TimeToLiveElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.RenderedEffect;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public class RenderedParticle extends AbstractParticle<RenderedEffect> {

    public RenderedParticle(ParticleData<RenderedEffect> data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER, isPublic);
    }

    @Override
    protected void show(Player player) {
        // ???
    }

    @Override
    protected void hide(Player player) {

    }
    @Override
    protected void perPlayerUpdate(Player player) {
        show(player);
    }

    @Override
    protected void globalUpdate() {

    }
}
