package dev.acrispycookie.crispycommons.utility.visual;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.Versioned;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class ParticleSpawner implements Versioned {

    private static final ParticleSpawner instance = VersionManager.createInstance(ParticleSpawner.class, new MappedVersions());

    public static ParticleSpawner newInstance() {
        return instance;
    }

    public static MappedVersions getRemapped() {
        return new MappedVersions();
    }

    public abstract void spawnColored(Player player, XParticle particle, Location location, float r, float g, float b);
    public abstract void spawnNormal(Player player, XParticle particle, Location location, int data);
}
