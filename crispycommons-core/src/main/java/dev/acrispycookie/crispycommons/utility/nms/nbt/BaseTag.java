package dev.acrispycookie.crispycommons.utility.nms.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;

public interface BaseTag {

    static BaseTag newInstance() {
        return VersionManager.createInstance(BaseTag.class, new MappedVersions());
    }
}
