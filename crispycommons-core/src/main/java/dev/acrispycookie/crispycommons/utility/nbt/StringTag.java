package dev.acrispycookie.crispycommons.utility.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;

public interface StringTag extends BaseTag {

    static StringTag newInstance(String value) {
        return VersionManager.createInstance(StringTag.class, new MappedVersions(), new ArgPair<>(String.class, value));
    }

    String getString();
}
