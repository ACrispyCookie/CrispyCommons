package dev.acrispycookie.crispycommons.utility.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;

public interface IntTag extends BaseTag {

    static IntTag newInstance(int value) {
        return VersionManager.createInstance(IntTag.class, new MappedVersions(), new ArgPair<>(int.class, value));
    }

    int getInt();
}
