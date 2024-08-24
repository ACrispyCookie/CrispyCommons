package dev.acrispycookie.crispycommons.nms.nbt;

import dev.acrispycookie.crispycommons.VersionManager;

public interface ListTag extends BaseTag {

    static ListTag newInstance() {
        return VersionManager.get().createInstance(ListTag.class);
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
