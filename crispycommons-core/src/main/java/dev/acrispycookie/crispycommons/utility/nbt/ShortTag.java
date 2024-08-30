package dev.acrispycookie.crispycommons.utility.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;

public interface ShortTag extends BaseTag {

    static ShortTag newInstance(short value) {
        return VersionManager.createInstance(ShortTag.class, new MappedVersions(), new ArgPair<>(short.class, value));
    }

    short getShort();
}
