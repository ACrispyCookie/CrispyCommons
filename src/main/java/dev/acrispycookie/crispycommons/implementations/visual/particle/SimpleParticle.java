package dev.acrispycookie.crispycommons.implementations.visual.particle;

import dev.acrispycookie.crispycommons.implementations.visual.particle.data.ParticleData;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import dev.acrispycookie.crispycommons.implementations.particle.SimpleEffect;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public class SimpleParticle extends AbstractParticle<SimpleEffect> {

    public SimpleParticle(ParticleData<SimpleEffect> data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER, isPublic);
    }

    @Override
    protected void show(Player player) {
        SimpleEffect effect = data.getElement().getFromContext(OfflinePlayer.class, player);
        Location location = data.getLocation().getFromContext(OfflinePlayer.class, player);
        player.spigot().playEffect(location, effect.getEffect(), effect.getData(), effect.getData(), 0, 0, 0, 1, 100, 160);
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
