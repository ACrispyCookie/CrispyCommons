package dev.acrispycookie.crispycommons.v1_8_R3.utility.visual;

import dev.acrispycookie.crispycommons.utility.visual.ParticleSpawner;
import dev.acrispycookie.crispycommons.utility.visual.XParticle;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ParticleSpawner_1_8_R3 extends ParticleSpawner {

    @Override
    public void spawnColored(Player player, XParticle particle, Location location, float r, float g, float b) {
        player.spigot().playEffect(location, getByXParticle(particle), 0, 1, r, g, b, 1, 0, 160);
    }

    @Override
    public void spawnNormal(Player player, XParticle particle, Location location, int data) {
        player.spigot().playEffect(location, getByXParticle(particle), data, data, 0, 0, 0, 1, 100, 160);
    }

    private Effect getByXParticle(XParticle particle) {
        if (Arrays.stream(Particle_1_8.values()).noneMatch((p) -> p.name().equals(particle.name())))
            return Effect.COLOURED_DUST;
        return Particle_1_8.valueOf(particle.name()).getEffect();
    }

    enum Particle_1_8 {
        DUST(Effect.COLOURED_DUST);

        private final Effect effect;

        Particle_1_8(Effect effect) {
            this.effect = effect;
        }

        public Effect getEffect() {
            return effect;
        }
    }
}
