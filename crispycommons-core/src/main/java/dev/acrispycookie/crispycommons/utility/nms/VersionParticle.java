package dev.acrispycookie.crispycommons.utility.nms;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.Versioned;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;

public interface VersionParticle extends Versioned {

    static VersionParticle newInstance() {
        return VersionManager.createInstance(VersionParticle.class, getRemapped());
    }

    static MappedVersions getRemapped() {
        return new MappedVersions();
    }

    boolean set(String particle);
}
