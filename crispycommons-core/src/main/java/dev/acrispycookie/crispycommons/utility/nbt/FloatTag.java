package dev.acrispycookie.crispycommons.utility.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;

public interface FloatTag extends BaseTag {

    static FloatTag newInstance(float value) {
        return VersionManager.createInstance(FloatTag.class, new MappedVersions(), new ArgPair<>(float.class, value));
    }

    float getFloat();
}
