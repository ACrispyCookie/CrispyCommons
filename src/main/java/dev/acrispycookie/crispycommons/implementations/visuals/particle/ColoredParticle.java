package dev.acrispycookie.crispycommons.implementations.visuals.particle;

import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.ColoredEffect;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public class ColoredParticle extends AbstractParticle<ColoredEffect> {

    public ColoredParticle(ParticleData<ColoredEffect> data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive);
    }

    @Override
    public void show(Player player) {
        ColoredEffect effect = data.getElement().getRaw();
        player.spigot().playEffect(effect.getLocation(), effect.getEffect(), 0, 1, effect.getRed(), effect.getGreen(), effect.getBlue(), 1, 0, 160);
    }

    @Override
    public void hide(Player player) {

    }

    @Override
    public void update(Player player) {
        show(player);
    }
}
