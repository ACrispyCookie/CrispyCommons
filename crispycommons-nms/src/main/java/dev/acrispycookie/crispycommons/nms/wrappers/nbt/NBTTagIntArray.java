package dev.acrispycookie.crispycommons.nms.wrappers.nbt;

import dev.acrispycookie.crispycommons.VersionManager;

public interface NBTTagIntArray extends NBTBase {

    static NBTTagIntArray newInstance(int[] value) {
        NBTTagIntArray tag = VersionManager.get().createInstance(NBTTagIntArray.class);
        tag.init(value);
        return tag;
    }

    void init(int[] value);
    int[] getIntArray();
}
