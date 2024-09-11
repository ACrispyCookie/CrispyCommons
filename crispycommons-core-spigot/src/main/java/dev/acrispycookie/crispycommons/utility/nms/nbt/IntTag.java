package dev.acrispycookie.crispycommons.utility.nms.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.jetbrains.annotations.NotNull;

public interface IntTag extends BaseTag {

    static @NotNull IntTag newInstance(int value) {
        return VersionManager.createInstance(IntTag.class, new MappedVersions(), new ArgPair<>(int.class, value));
    }

    int getInt();
}
