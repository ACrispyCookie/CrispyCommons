package dev.acrispycookie.crispycommons.utility.visual;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.Versioned;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class ParticleSpawner implements Versioned {

    private static final ParticleSpawner instance = VersionManager.createInstance(ParticleSpawner.class, new MappedVersions());

    public static @NotNull ParticleSpawner newInstance() {
        return instance;
    }

    public static @NotNull MappedVersions getRemapped() {
        return new MappedVersions();
    }

    public abstract void spawnColored(@NotNull Player player, @NotNull XParticle particle, @NotNull Location location, float r, float g, float b);
    public abstract void spawnNormal(@NotNull Player player, @NotNull XParticle particle, @NotNull Location location, int data);
}
