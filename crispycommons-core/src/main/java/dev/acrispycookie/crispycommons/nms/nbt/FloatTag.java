package dev.acrispycookie.crispycommons.nms.nbt;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;
import dev.acrispycookie.crispycommons.utility.version.MappedVersions;

public interface FloatTag extends BaseTag {

    static FloatTag newInstance(float value) {
        return VersionManager.get().createInstance(FloatTag.class, new MappedVersions(), new ArgPair<>(float.class, value));
    }

    float getFloat();
}
