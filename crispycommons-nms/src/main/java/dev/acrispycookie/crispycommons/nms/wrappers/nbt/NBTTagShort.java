package dev.acrispycookie.crispycommons.nms.wrappers.nbt;

import dev.acrispycookie.crispycommons.VersionManager;

public interface NBTTagShort extends NBTBase {

    static NBTTagShort newInstance(short value) {
        NBTTagShort tag = VersionManager.get().createInstance(NBTTagShort.class);
        tag.init(value);
        return tag;
    }

    void init(short value);
    short getShort();
}
