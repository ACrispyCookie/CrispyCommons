package dev.acrispycookie.crispycommons.nms.nbt;

import dev.acrispycookie.crispycommons.VersionManager;

public interface BaseTag {

    static BaseTag newInstance() {
        return VersionManager.get().createInstance(BaseTag.class);
    }
}
