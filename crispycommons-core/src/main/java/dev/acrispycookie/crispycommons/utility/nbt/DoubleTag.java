package dev.acrispycookie.crispycommons.utility.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;

public interface DoubleTag extends BaseTag {

    static DoubleTag newInstance(double value) {
        return VersionManager.createInstance(DoubleTag.class, new MappedVersions(), new ArgPair<>(double.class, value));
    }

    double getDouble();
}
