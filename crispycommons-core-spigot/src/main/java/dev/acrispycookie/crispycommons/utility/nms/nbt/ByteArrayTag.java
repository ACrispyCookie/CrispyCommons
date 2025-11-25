package dev.acrispycookie.crispycommons.utility.nms.nbt;

import dev.acrispycookie.crispycommons.utility.nms.nbt.BaseTag;
import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.jetbrains.annotations.NotNull;

public interface ByteArrayTag extends BaseTag {

    static @NotNull ByteArrayTag newInstance(byte[] value) {
        return VersionManager.createInstance(ByteArrayTag.class, new MappedVersions(), new ArgPair<>(byte[].class, value));
    }

    byte[] getByteArray();
}
