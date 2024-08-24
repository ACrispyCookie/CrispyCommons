package dev.acrispycookie.crispycommons.nms.nbt;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;
import dev.acrispycookie.crispycommons.utility.version.MappedVersions;

public interface ByteTag extends BaseTag {

    static ByteTag newInstance(byte value) {
        return VersionManager.get().createInstance(ByteTag.class, new MappedVersions(), new ArgPair<>(byte.class, value));
    }

    byte getByte();
}
