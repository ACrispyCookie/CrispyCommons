package dev.acrispycookie.crispycommons.implementations.visuals.particle;

import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.RenderedEffect;
import org.bukkit.entity.Player;

import java.util.Set;

public class RenderedParticle extends AbstractParticle<RenderedEffect> {

    public RenderedParticle(ParticleData<RenderedEffect> data, Set<? extends Player> receivers) {
        super(data, receivers);
    }

    @Override
    public void playOnce(Player player) {
        RenderedEffect effect = data.getElement().getRaw();
        playRendered(player, effect);
    }

    private void playRendered(Player p, RenderedEffect effect) {
        //??
    }

    @Override
    public void update() {

    }
}
