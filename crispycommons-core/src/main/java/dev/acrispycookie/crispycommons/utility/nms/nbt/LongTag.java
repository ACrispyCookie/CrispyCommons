package dev.acrispycookie.crispycommons.utility.nms.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;

public interface LongTag extends BaseTag {

    static LongTag newInstance(long value) {
        return VersionManager.createInstance(LongTag.class, new MappedVersions(), new ArgPair<>(long.class, value));
    }

    long getLong();
}
