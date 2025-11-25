package dev.acrispycookie.crispycommons.utility.nms.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.jetbrains.annotations.NotNull;

public interface StringTag extends BaseTag {

    static @NotNull StringTag newInstance(@NotNull String value) {
        return VersionManager.createInstance(StringTag.class, new MappedVersions(), new ArgPair<>(String.class, value));
    }

    @NotNull String getString();
}
