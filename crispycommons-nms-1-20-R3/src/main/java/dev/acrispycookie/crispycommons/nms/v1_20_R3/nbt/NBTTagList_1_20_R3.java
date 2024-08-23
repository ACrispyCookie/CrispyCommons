package dev.acrispycookie.crispycommons.nms.v1_20_R3.nbt;

import dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTBase;
import dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagCompound;
import dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagDouble;
import dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagFloat;
import dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagIntArray;
import dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagString;
import net.minecraft.nbt.NBTTagList;

public class NBTTagList_1_20_R3 extends NBTBase_1_20_R3 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagList {

    private final NBTTagList tag;

    public NBTTagList getInternal() {
        return tag;
    }

    public NBTTagList_1_20_R3() {
        tag = new NBTTagList();
    }

    @Override
    public void add(NBTBase nbtbase) {
        tag.add(((NBTBase_1_20_R3) nbtbase).getInternal());
    }

    @Override
    public void add(int index, NBTBase nbtbase) {
        tag.c(index, ((NBTBase_1_20_R3) nbtbase).getInternal());
    }

    @Override
    public NBTBase remove(int index) {
        NBTBase_1_20_R3 base = new NBTTagList_1_20_R3();
        base.setBase(tag.c(index));
        return base;
    }

    @Override
    public NBTTagCompound getCompound(int index) {
        return new NBTTagCompound_1_20_R3(tag.a(index));
    }

    @Override
    public NBTTagDouble getDouble(int index) {
        return new NBTTagDouble_1_20_R3(tag.h(index));
    }

    @Override
    public NBTTagFloat getFloat(int index) {
        return new NBTTagFloat_1_20_R3(tag.i(index));
    }

    @Override
    public NBTTagIntArray getIntArray(int index) {
        return new NBTTagIntArray_1_20_R3(tag.f(index));
    }

    @Override
    public NBTTagString getString(int index) {
        return new NBTTagString_1_20_R3(tag.j(index));
    }

    @Override
    public NBTBase get(int index) {
        NBTBase_1_20_R3 base = new NBTTagList_1_20_R3();
        base.setBase(tag.k(index));
        return base;
    }
}
