package dev.acrispycookie.crispycommons.nms.wrappers.nbt;

import dev.acrispycookie.crispycommons.VersionManager;

public interface NBTBase {

    static NBTBase newInstance() {
        return VersionManager.get().createInstance(NBTBase.class);
    }
}
