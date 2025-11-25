package dev.acrispycookie.crispycommons.utility.nms.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.ArgPair;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.jetbrains.annotations.NotNull;

public interface ShortTag extends BaseTag {

    static @NotNull ShortTag newInstance(short value) {
        return VersionManager.createInstance(ShortTag.class, new MappedVersions(), new ArgPair<>(short.class, value));
    }

    short getShort();
}
