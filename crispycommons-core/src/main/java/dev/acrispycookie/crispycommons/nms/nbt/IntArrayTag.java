package dev.acrispycookie.crispycommons.nms.nbt;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;
import dev.acrispycookie.crispycommons.utility.version.MappedVersions;

public interface IntArrayTag extends BaseTag {

    static IntArrayTag newInstance(int[] value) {
        return VersionManager.get().createInstance(IntArrayTag.class, new MappedVersions(), new ArgPair<>(int[].class, value));
    }

    int[] getIntArray();
}
