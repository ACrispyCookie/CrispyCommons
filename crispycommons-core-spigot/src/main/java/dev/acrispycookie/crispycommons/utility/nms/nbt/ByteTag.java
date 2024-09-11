package dev.acrispycookie.crispycommons.utility.nms.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.jetbrains.annotations.NotNull;

public interface ByteTag extends BaseTag {

    static @NotNull ByteTag newInstance(byte value) {
        return VersionManager.createInstance(ByteTag.class, new MappedVersions(), new ArgPair<>(byte.class, value));
    }

    byte getByte();
}
