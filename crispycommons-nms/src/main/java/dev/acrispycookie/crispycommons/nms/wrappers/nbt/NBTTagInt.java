package dev.acrispycookie.crispycommons.nms.wrappers.nbt;

import dev.acrispycookie.crispycommons.VersionManager;

public interface NBTTagInt extends NBTBase {

    static NBTTagInt newInstance(int value) {
        NBTTagInt tag = VersionManager.get().createInstance(NBTTagInt.class);
        tag.init(value);
        return tag;
    }

    void init(int value);
    int getInt();
}
