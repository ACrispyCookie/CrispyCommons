package dev.acrispycookie.crispycommons.utility.nms.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.jetbrains.annotations.NotNull;

public interface ListTag extends BaseTag {

    static @NotNull ListTag newInstance() {
        return VersionManager.createInstance(ListTag.class, new MappedVersions());
    }

    void add(@NotNull BaseTag nbtbase);
    void add(int index, @NotNull BaseTag nbtbase);
    @NotNull BaseTag remove(int index);
    @NotNull CompoundTag getCompound(int index);
    @NotNull DoubleTag getDouble(int index);
    @NotNull FloatTag getFloat(int index);
    @NotNull IntArrayTag getIntArray(int index);
    @NotNull StringTag getString(int index);
    @NotNull BaseTag get(int index);
}
