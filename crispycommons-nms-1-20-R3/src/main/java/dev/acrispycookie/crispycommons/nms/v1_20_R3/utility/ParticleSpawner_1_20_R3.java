package dev.acrispycookie.crispycommons.nms.v1_20_R3.utility;

import com.cryptomorin.xseries.particles.XParticle;
import dev.acrispycookie.crispycommons.nms.wrappers.utilities.ParticleSpawner;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleSpawner_1_20_R3 implements ParticleSpawner {


    @Override
    public void spawnColored(Player player, XParticle particle, Location location, float r, float g, float b) {
        int red = (int) (r * 255);
        int green = (int) (g * 255);
        int blue = (int) (b * 255);
        player.spawnParticle(particle.get(), location, 100, 0, 0, 0, 1,
                new Particle.DustOptions(Color.fromRGB(red, green, blue), 1));
    }

    @Override
    public void spawnNormal(Player player, XParticle particle, Location location, int data) {
        player.spawnParticle(particle.get(), location, data);
    }
}
