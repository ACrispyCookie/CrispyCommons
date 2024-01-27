package dev.acrispycookie.crispycommons.implementations.visuals.particle;

import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.SimpleEffect;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public class SimpleParticle extends AbstractParticle<SimpleEffect> {

    public SimpleParticle(ParticleData<SimpleEffect> data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive);
    }

    @Override
    public void playOnce(Player player) {
        SimpleEffect effect = data.getElement().getRaw();
        player.spigot().playEffect(effect.getLocation(), effect.getEffect(), effect.getData(), effect.getData(), 0, 0, 0, 1, 100, 160);
    }
}
