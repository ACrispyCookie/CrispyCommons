package dev.acrispycookie.crispycommons.nms.v1_8_R3.nbt;

import dev.acrispycookie.crispycommons.nms.wrappers.nbt.*;

public class NBTTagList_1_8_R3 extends NBTBase_1_8_R3 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagList {

    private final net.minecraft.server.v1_8_R3.NBTTagList tag;

    public net.minecraft.server.v1_8_R3.NBTTagList getInternal() {
        return tag;
    }

    public NBTTagList_1_8_R3() {
        tag = new net.minecraft.server.v1_8_R3.NBTTagList();
    }

    @Override
    public void add(NBTBase nbtbase) {
        tag.add(((NBTBase_1_8_R3) nbtbase).getInternal());
    }

    @Override
    public void add(int index, NBTBase nbtbase) {
        tag.a(index, ((NBTBase_1_8_R3) nbtbase).getInternal());
    }

    @Override
    public NBTBase remove(int index) {
        NBTBase_1_8_R3 base = new NBTTagList_1_8_R3();
        base.setBase(tag.a(index));
        return base;
    }

    @Override
    public NBTTagCompound getCompound(int index) {
        return new NBTTagCompound_1_8_R3(tag.get(index));
    }

    @Override
    public NBTTagDouble getDouble(int index) {
        return new NBTTagDouble_1_8_R3(tag.d(index));
    }

    @Override
    public NBTTagFloat getFloat(int index) {
        return new NBTTagFloat_1_8_R3(tag.e(index));
    }

    @Override
    public NBTTagIntArray getIntArray(int index) {
        return new NBTTagIntArray_1_8_R3(tag.c(index));
    }

    @Override
    public NBTTagString getString(int index) {
        return new NBTTagString_1_8_R3(tag.getString(index));
    }

    @Override
    public NBTBase get(int index) {
        NBTBase_1_8_R3 base = new NBTTagList_1_8_R3();
        base.setBase(tag.g(index));
        return base;
    }
}
