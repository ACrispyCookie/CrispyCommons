package dev.acrispycookie.crispycommons.utility.nms.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.jetbrains.annotations.NotNull;

public interface FloatTag extends BaseTag {

    static @NotNull FloatTag newInstance(float value) {
        return VersionManager.createInstance(FloatTag.class, new MappedVersions(), new ArgPair<>(float.class, value));
    }

    float getFloat();
}
