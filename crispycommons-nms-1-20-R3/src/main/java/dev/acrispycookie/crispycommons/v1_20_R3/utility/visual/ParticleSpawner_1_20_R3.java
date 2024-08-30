package dev.acrispycookie.crispycommons.v1_20_R3.utility.visual;

import dev.acrispycookie.crispycommons.utility.visual.ParticleSpawner;
import dev.acrispycookie.crispycommons.utility.visual.XParticle;
import dev.acrispycookie.crispycommons.v1_20_R3.utility.nms.VersionParticle_1_20_R3;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleSpawner_1_20_R3 extends ParticleSpawner {


    @Override
    public void spawnColored(Player player, XParticle particle, Location location, float r, float g, float b) {
        int red = (int) (r * 255);
        int green = (int) (g * 255);
        int blue = (int) (b * 255);
        player.spawnParticle(getByXParticle(particle), location, 10, 0, 0, 0, 1,
                new Particle.DustOptions(Color.fromRGB(red, green, blue), 1));
    }

    @Override
    public void spawnNormal(Player player, XParticle particle, Location location, int data) {
        player.spawnParticle(getByXParticle(particle), location, data);
    }

    private Particle getByXParticle(XParticle particle) {
        return ((VersionParticle_1_20_R3) particle.get()).getParticle();
    }
}
