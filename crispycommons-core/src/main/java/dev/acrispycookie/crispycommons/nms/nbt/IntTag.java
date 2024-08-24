package dev.acrispycookie.crispycommons.nms.nbt;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;
import dev.acrispycookie.crispycommons.utility.version.MappedVersions;

public interface IntTag extends BaseTag {

    static IntTag newInstance(int value) {
        return VersionManager.get().createInstance(IntTag.class, new MappedVersions(), new ArgPair<>(int.class, value));
    }

    int getInt();
}
