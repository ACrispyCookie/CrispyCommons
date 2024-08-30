package dev.acrispycookie.crispycommons.utility.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;

public interface ByteTag extends BaseTag {

    static ByteTag newInstance(byte value) {
        return VersionManager.createInstance(ByteTag.class, new MappedVersions(), new ArgPair<>(byte.class, value));
    }

    byte getByte();
}
