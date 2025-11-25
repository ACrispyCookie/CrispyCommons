package dev.acrispycookie.crispycommons.utility.nms;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.Versioned;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.jetbrains.annotations.NotNull;

public interface VersionParticle extends Versioned {

    static @NotNull VersionParticle newInstance() {
        return VersionManager.createInstance(VersionParticle.class, getRemapped());
    }

    static @NotNull MappedVersions getRemapped() {
        return new MappedVersions();
    }

    boolean set(@NotNull String particle);
}
