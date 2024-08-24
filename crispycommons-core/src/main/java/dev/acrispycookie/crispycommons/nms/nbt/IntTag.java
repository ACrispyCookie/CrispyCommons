package dev.acrispycookie.crispycommons.nms.nbt;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;

public interface IntTag extends BaseTag {

    static IntTag newInstance(int value) {
        return VersionManager.get().createInstance(IntTag.class, new ArgPair<>(int.class, value));
    }

    int getInt();
}
