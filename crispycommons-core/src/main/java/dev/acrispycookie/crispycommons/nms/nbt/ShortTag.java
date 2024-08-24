package dev.acrispycookie.crispycommons.nms.nbt;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.ArgPair;
import dev.acrispycookie.crispycommons.utility.version.MappedVersions;

public interface ShortTag extends BaseTag {

    static ShortTag newInstance(short value) {
        return VersionManager.get().createInstance(ShortTag.class, new MappedVersions(), new ArgPair<>(short.class, value));
    }

    short getShort();
}
