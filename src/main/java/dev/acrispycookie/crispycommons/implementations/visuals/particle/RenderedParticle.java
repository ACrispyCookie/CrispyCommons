package dev.acrispycookie.crispycommons.implementations.visuals.particle;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.ParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.particle.AbstractCrispyParticle;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.RenderedEffect;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.SimpleEffect;
import org.bukkit.entity.Player;

import java.util.Set;

public class RenderedParticle extends AbstractCrispyParticle<RenderedEffect> {

    public RenderedParticle(ParticleElement<RenderedEffect> effect, long duration, long period, Set<? extends Player> receivers) {
        super(effect, duration, period, receivers);
    }

    public RenderedParticle(ParticleElement<RenderedEffect> effect, Set<? extends Player> receivers) {
        super(effect, receivers);
    }

    @Override
    public void playOnce(Player player) {
        RenderedEffect effect = content.getRaw();
        playRendered(player, effect);
    }

    private void playRendered(Player p, RenderedEffect effect) {
        //??
    }

    @Override
    public void update() {

    }
}
