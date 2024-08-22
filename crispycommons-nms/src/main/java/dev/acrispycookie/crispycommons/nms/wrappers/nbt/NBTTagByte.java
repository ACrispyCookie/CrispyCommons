package dev.acrispycookie.crispycommons.nms.wrappers.nbt;

import dev.acrispycookie.crispycommons.VersionManager;

public interface NBTTagByte extends NBTBase {

    static NBTTagByte newInstance(byte value) {
        NBTTagByte tag = VersionManager.get().createInstance(NBTTagByte.class);
        tag.init(value);
        return tag;
    }

    void init(byte value);
    byte getByte();
}
