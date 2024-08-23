package dev.acrispycookie.crispycommons.nms.v1_20_R3.nbt;

import net.minecraft.nbt.NBTTagInt;

public class NBTTagInt_1_20_R3 extends NBTBase_1_20_R3 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagInt {

    private NBTTagInt tag;

    public NBTTagInt_1_20_R3(int value) {
        this.tag = NBTTagInt.a(value);
    }

    public NBTTagInt getInternal() {
        return tag;
    }

    @Override
    public void init(int value) {
        tag = NBTTagInt.a(value);
    }

    @Override
    public int getInt() {
        return tag.g();
    }
}
