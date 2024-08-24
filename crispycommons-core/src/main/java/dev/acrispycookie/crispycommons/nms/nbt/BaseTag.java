package dev.acrispycookie.crispycommons.nms.nbt;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.MappedVersions;

public interface BaseTag {

    static BaseTag newInstance() {
        return VersionManager.get().createInstance(BaseTag.class, new MappedVersions());
    }
}
