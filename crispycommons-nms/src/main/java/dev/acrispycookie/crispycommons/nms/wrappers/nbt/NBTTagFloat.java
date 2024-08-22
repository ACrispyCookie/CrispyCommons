package dev.acrispycookie.crispycommons.nms.wrappers.nbt;

import dev.acrispycookie.crispycommons.VersionManager;

public interface NBTTagFloat extends NBTBase {

    static NBTTagFloat newInstance(float value) {
        NBTTagFloat tag = VersionManager.get().createInstance(NBTTagFloat.class);
        tag.init(value);
        return tag;
    }

    void init(float value);
    float getFloat();
}
