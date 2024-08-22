package dev.acrispycookie.crispycommons.nms.wrappers.nbt;

import dev.acrispycookie.crispycommons.VersionManager;

public interface NBTTagByteArray extends NBTBase {

    static NBTTagByteArray newInstance(byte[] value) {
        NBTTagByteArray tag = VersionManager.get().createInstance(NBTTagByteArray.class);
        tag.init(value);
        return tag;
    }

    void init(byte[] value);
    byte[] getByteArray();
}
