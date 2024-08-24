package dev.acrispycookie.crispycommons.nms.nbt;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;

public interface DoubleTag extends BaseTag {

    static DoubleTag newInstance(double value) {
        return VersionManager.get().createInstance(DoubleTag.class, new ArgPair<>(double.class, value));
    }

    double getDouble();
}
