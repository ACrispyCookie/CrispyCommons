package dev.acrispycookie.crispycommons.implementations.visuals.particle;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.ParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.particle.AbstractCrispyParticle;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.ColoredEffect;
import org.bukkit.entity.Player;

import java.util.Set;

public class ColoredParticle extends AbstractCrispyParticle<ColoredEffect> {

    public ColoredParticle(ParticleElement<ColoredEffect> effect, long duration, long period, Set<? extends Player> receivers) {
        super(effect, duration, period, receivers);
    }

    public ColoredParticle(ParticleElement<ColoredEffect> effect, Set<? extends Player> receivers) {
        super(effect, receivers);
    }

    @Override
    public void playOnce(Player player) {
        ColoredEffect effect = content.getRaw();
        player.spigot().playEffect(effect.getLocation(), effect.getEffect(), 0, 1, effect.getRed(), effect.getGreen(), effect.getBlue(), 1, 0, 160);
    }

    @Override
    public void update() {

    }
}
