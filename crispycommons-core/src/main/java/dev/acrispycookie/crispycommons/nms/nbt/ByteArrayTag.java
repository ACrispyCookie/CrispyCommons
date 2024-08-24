package dev.acrispycookie.crispycommons.nms.nbt;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;

public interface ByteArrayTag extends BaseTag {

    static ByteArrayTag newInstance(byte[] value) {
        return VersionManager.get().createInstance(ByteArrayTag.class, new ArgPair<>(byte[].class, value));
    }

    byte[] getByteArray();
}
