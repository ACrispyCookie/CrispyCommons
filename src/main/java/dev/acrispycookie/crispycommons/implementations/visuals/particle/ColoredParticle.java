package dev.acrispycookie.crispycommons.implementations.visuals.particle;

import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.ColoredEffect;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public class ColoredParticle extends AbstractParticle<ColoredEffect> {

    public ColoredParticle(ParticleData<ColoredEffect> data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long, ?> timeToLive) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER);
    }

    @Override
    protected void show(Player player) {
        ColoredEffect effect = data.getElement().getFromContext(OfflinePlayer.class, player);
        Location location = data.getLocation().getFromContext(OfflinePlayer.class, player);
        player.spigot().playEffect(location, effect.getEffect(), 0, 1, effect.getNormalisedRed(), effect.getNormalisedGreen(), effect.getNormalisedBlue(),
                1, 0, 160);
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
