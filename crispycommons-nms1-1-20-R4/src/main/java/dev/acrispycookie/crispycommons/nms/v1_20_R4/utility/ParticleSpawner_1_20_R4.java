package dev.acrispycookie.crispycommons.nms.v1_20_R4.utility;

import com.cryptomorin.xseries.particles.XParticle;
import dev.acrispycookie.crispycommons.nms.wrappers.utilities.ParticleSpawner;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ParticleSpawner_1_20_R4 implements ParticleSpawner {


    @Override
    public void spawnColored(Player player, XParticle particle, Location location, float r, float g, float b) {
        player.spawnParticle();
        player.spigot().playEffect(location, getByXParticle(particle), 0, 1, r, g, b, 1, 0, 160);
    }

    @Override
    public void spawnNormal(Player player, XParticle particle, Location location, int data) {
        player.spawnParticle(particle.get(), location, data);
    }
}
