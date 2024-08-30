package dev.acrispycookie.crispycommons.utility.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;

public interface IntArrayTag extends BaseTag {

    static IntArrayTag newInstance(int[] value) {
        return VersionManager.createInstance(IntArrayTag.class, new MappedVersions(), new ArgPair<>(int[].class, value));
    }

    int[] getIntArray();
}
