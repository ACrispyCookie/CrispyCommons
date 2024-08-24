package dev.acrispycookie.crispycommons.nms.nbt;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;
import dev.acrispycookie.crispycommons.utility.version.MappedVersions;

public interface DoubleTag extends BaseTag {

    static DoubleTag newInstance(double value) {
        return VersionManager.get().createInstance(DoubleTag.class, new MappedVersions(), new ArgPair<>(double.class, value));
    }

    double getDouble();
}
