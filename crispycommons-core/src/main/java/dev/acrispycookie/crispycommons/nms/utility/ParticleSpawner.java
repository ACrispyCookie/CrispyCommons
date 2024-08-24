package dev.acrispycookie.crispycommons.nms.utility;

import com.cryptomorin.xseries.particles.XParticle;
import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.MappedVersions;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface ParticleSpawner {

    ParticleSpawner instance = VersionManager.get().createInstance(ParticleSpawner.class, new MappedVersions());

    static ParticleSpawner newInstance() {
        return instance;
    }

    void spawnColored(Player player, XParticle particle, Location location, float r, float g, float b);
    void spawnNormal(Player player, XParticle particle, Location location, int data);
}
