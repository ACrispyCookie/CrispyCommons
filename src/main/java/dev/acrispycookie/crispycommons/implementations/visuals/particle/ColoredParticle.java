package dev.acrispycookie.crispycommons.implementations.visuals.particle;

import dev.acrispycookie.crispycommons.api.visuals.particle.AbstractCrispyParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.ColoredEffect;
import org.bukkit.entity.Player;

import java.util.Set;

public class ColoredParticle extends AbstractCrispyParticle<ColoredEffect> {

    public ColoredParticle(ParticleData<ColoredEffect> data, Set<? extends Player> receivers) {
        super(data, receivers);
    }

    @Override
    public void playOnce(Player player) {
        ColoredEffect effect = data.getElement().getRaw();
        player.spigot().playEffect(effect.getLocation(), effect.getEffect(), 0, 1, effect.getRed(), effect.getGreen(), effect.getBlue(), 1, 0, 160);
    }

    @Override
    public void update() {

    }
}
