package dev.acrispycookie.crispycommons.nms.wrappers.nbt;

import dev.acrispycookie.crispycommons.VersionManager;

public interface NBTTagString extends NBTBase {

    static NBTTagString newInstance(String value) {
        NBTTagString tag = VersionManager.get().createInstance(NBTTagString.class);
        tag.init(value);
        return tag;
    }

    void init(String value);
    String getString();
}
