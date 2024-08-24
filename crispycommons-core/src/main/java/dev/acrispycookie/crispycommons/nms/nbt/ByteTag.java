package dev.acrispycookie.crispycommons.nms.nbt;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;

public interface ByteTag extends BaseTag {

    static ByteTag newInstance(byte value) {
        return VersionManager.get().createInstance(ByteTag.class, new ArgPair<>(byte.class, value));
    }

    byte getByte();
}
