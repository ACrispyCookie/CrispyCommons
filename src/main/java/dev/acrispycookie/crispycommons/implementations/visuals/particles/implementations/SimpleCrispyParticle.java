package dev.acrispycookie.crispycommons.implementations.visuals.particles.implementations;

import dev.acrispycookie.crispycommons.implementations.visuals.particles.AbstractCrispyParticle;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.CrispyEffect;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.implementations.ColoredEffect;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.implementations.SimpleEffect;
import dev.acrispycookie.crispycommons.utility.elements.implementations.particles.ParticleElement;
import org.bukkit.entity.Player;

import java.util.Set;

public class SimpleCrispyParticle<T extends CrispyEffect> extends AbstractCrispyParticle<T> {

    public SimpleCrispyParticle(ParticleElement<T> effect, long duration, long period, Set<? extends Player> receivers) {
        super(effect, duration, period, receivers);
    }

    public SimpleCrispyParticle(ParticleElement<T> effect, Set<? extends Player> receivers) {
        super(effect, receivers);
    }

    @Override
    public void playOnce(Player player) {
        T effect = element.getContent();
        if(effect instanceof SimpleEffect) {
            playSimple(player, (SimpleEffect) effect);
        } else if (effect instanceof ColoredEffect) {
            playColored(player, (ColoredEffect) effect);
        } else {
            playRendered();
        }
    }

    private void playSimple(Player player, SimpleEffect effect) {
        player.spigot().playEffect(effect.getLocation(), effect.getEffect(), effect.getData(), effect.getData(), 0, 0, 0, 1, 100, 160);
    }

    private void playColored(Player player, ColoredEffect effect) {
        player.spigot().playEffect(effect.getLocation(), effect.getEffect(), 0, 1, effect.getRed(), effect.getGreen(), effect.getBlue(), 1, 0, 160);
    }

    private void playRendered() {
        //??
    }

    @Override
    public void update() {

    }
}
