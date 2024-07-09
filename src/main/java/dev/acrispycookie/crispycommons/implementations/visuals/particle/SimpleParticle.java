package dev.acrispycookie.crispycommons.implementations.visuals.particle;

import dev.acrispycookie.crispycommons.api.wrappers.elements.PersonalElement;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalParticleElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalParticleElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.SimpleEffect;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public class SimpleParticle extends AbstractParticle<SimpleEffect> {

    public SimpleParticle(ParticleData<SimpleEffect> data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER);
    }

    @Override
    protected void show(Player player) {
        SimpleEffect effect = data.getElement() instanceof GlobalParticleElement<?> ?
                ((GlobalParticleElement<SimpleEffect>) data.getElement()).getRaw() :
                ((PersonalParticleElement<SimpleEffect>) data.getElement()).getRaw(player);
        player.spigot().playEffect(effect.getLocation(), effect.getEffect(), effect.getData(), effect.getData(), 0, 0, 0, 1, 100, 160);
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
