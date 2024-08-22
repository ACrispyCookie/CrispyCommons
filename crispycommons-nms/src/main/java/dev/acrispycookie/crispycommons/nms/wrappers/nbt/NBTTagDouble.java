package dev.acrispycookie.crispycommons.nms.wrappers.nbt;

import dev.acrispycookie.crispycommons.VersionManager;

public interface NBTTagDouble extends NBTBase {

    static NBTTagDouble newInstance(double value) {
        NBTTagDouble tag = VersionManager.get().createInstance(NBTTagDouble.class);
        tag.init(value);
        return tag;
    }

    void init(double value);
    double getDouble();
}
