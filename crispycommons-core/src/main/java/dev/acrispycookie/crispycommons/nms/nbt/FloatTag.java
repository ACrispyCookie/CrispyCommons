package dev.acrispycookie.crispycommons.nms.nbt;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;

public interface FloatTag extends BaseTag {

    static FloatTag newInstance(float value) {
        return VersionManager.get().createInstance(FloatTag.class, new ArgPair<>(float.class, value));
    }

    float getFloat();
}
