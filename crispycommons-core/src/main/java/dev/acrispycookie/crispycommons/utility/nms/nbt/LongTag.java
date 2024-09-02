package dev.acrispycookie.crispycommons.utility.nms.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.jetbrains.annotations.NotNull;

public interface LongTag extends BaseTag {

    static @NotNull LongTag newInstance(long value) {
        return VersionManager.createInstance(LongTag.class, new MappedVersions(), new ArgPair<>(long.class, value));
    }

    long getLong();
}
