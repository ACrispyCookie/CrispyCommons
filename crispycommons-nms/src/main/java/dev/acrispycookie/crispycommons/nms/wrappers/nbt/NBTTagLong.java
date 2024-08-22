package dev.acrispycookie.crispycommons.nms.wrappers.nbt;

import dev.acrispycookie.crispycommons.VersionManager;

public interface NBTTagLong extends NBTBase {

    static NBTTagLong newInstance(long value) {
        NBTTagLong tag = VersionManager.get().createInstance(NBTTagLong.class);
        tag.init(value);
        return tag;
    }

    void init(long value);
    long getLong();
}
