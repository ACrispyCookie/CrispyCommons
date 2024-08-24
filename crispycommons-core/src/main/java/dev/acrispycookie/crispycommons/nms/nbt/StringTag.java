package dev.acrispycookie.crispycommons.nms.nbt;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;
import dev.acrispycookie.crispycommons.utility.version.MappedVersions;

public interface StringTag extends BaseTag {

    static StringTag newInstance(String value) {
        return VersionManager.get().createInstance(StringTag.class, new MappedVersions(), new ArgPair<>(String.class, value));
    }

    String getString();
}
