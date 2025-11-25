package dev.acrispycookie.crispycommons.utility.nms.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.jetbrains.annotations.NotNull;

public interface DoubleTag extends BaseTag {

    static @NotNull DoubleTag newInstance(double value) {
        return VersionManager.createInstance(DoubleTag.class, new MappedVersions(), new ArgPair<>(double.class, value));
    }

    double getDouble();
}
