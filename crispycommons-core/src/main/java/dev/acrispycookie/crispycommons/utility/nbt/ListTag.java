package dev.acrispycookie.crispycommons.utility.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;

public interface ListTag extends BaseTag {

    static ListTag newInstance() {
        return VersionManager.createInstance(ListTag.class, new MappedVersions());
    }

    void add(BaseTag nbtbase);
    void add(int index, BaseTag nbtbase);
    BaseTag remove(int index);
    CompoundTag getCompound(int index);
    DoubleTag getDouble(int index);
    FloatTag getFloat(int index);
    IntArrayTag getIntArray(int index);
    StringTag getString(int index);
    BaseTag get(int index);
}
