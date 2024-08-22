package dev.acrispycookie.crispycommons.nms.wrappers.nbt;

import dev.acrispycookie.crispycommons.VersionManager;

public interface NBTTagList extends NBTBase {

    static NBTTagList newInstance() {
        return VersionManager.get().createInstance(NBTTagList.class);
    }

    void add(NBTBase nbtbase);
    void add(int index, NBTBase nbtbase);
    NBTBase remove(int index);
    NBTTagCompound getCompound(int index);
    NBTTagDouble getDouble(int index);
    NBTTagFloat getFloat(int index);
    NBTTagIntArray getIntArray(int index);
    NBTTagString getString(int index);
    NBTBase get(int index);
}
