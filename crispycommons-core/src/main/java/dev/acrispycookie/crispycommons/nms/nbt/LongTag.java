package dev.acrispycookie.crispycommons.nms.nbt;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;
import dev.acrispycookie.crispycommons.utility.version.MappedVersions;

public interface LongTag extends BaseTag {

    static LongTag newInstance(long value) {
        return VersionManager.get().createInstance(LongTag.class, new MappedVersions(), new ArgPair<>(long.class, value));
    }

    long getLong();
}
