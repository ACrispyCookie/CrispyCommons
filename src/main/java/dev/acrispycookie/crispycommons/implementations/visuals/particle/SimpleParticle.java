package dev.acrispycookie.crispycommons.implementations.visuals.particle;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.SimpleParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.particle.AbstractCrispyParticle;
import dev.acrispycookie.crispycommons.api.wrappers.particle.CrispyEffect;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.ColoredEffect;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.SimpleEffect;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.ParticleElement;
import org.bukkit.entity.Player;

import java.util.Set;

public class SimpleParticle extends AbstractCrispyParticle<SimpleEffect> {

    public SimpleParticle(ParticleElement<SimpleEffect> effect, long duration, long period, Set<? extends Player> receivers) {
        super(effect, duration, period, receivers);
    }

    public SimpleParticle(ParticleElement<SimpleEffect> effect, Set<? extends Player> receivers) {
        super(effect, receivers);
    }

    @Override
    public void playOnce(Player player) {
        SimpleEffect effect = content.getRaw();
        player.spigot().playEffect(effect.getLocation(), effect.getEffect(), effect.getData(), effect.getData(), 0, 0, 0, 1, 100, 160);
    }

    @Override
    public void update() {

    }
}
