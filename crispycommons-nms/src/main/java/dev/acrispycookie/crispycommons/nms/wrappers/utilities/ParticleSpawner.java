package dev.acrispycookie.crispycommons.nms.wrappers.utilities;

import com.cryptomorin.xseries.particles.XParticle;
import dev.acrispycookie.crispycommons.VersionManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface ParticleSpawner {

    static ParticleSpawner newInstance() {
        return VersionManager.get().createInstance(ParticleSpawner.class);
    }

    void spawnColored(Player player, XParticle particle, Location location, float r, float g, float b);
    void spawnNormal(Player player, XParticle particle, Location location, int data);
}
