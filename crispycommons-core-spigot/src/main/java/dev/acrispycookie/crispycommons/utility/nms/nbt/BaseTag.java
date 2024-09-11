package dev.acrispycookie.crispycommons.utility.nms.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.jetbrains.annotations.NotNull;

public interface BaseTag {

    static @NotNull BaseTag newInstance() {
        return VersionManager.createInstance(BaseTag.class, new MappedVersions());
    }
}
