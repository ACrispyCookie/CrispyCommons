package dev.acrispycookie.crispycommons.nms.v1_8_R3.nbt;

import net.minecraft.server.v1_8_R3.NBTTagInt;

public class NBTTagInt_1_8_R3 extends NBTBase_1_8_R3 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagInt {

    private net.minecraft.server.v1_8_R3.NBTTagInt tag;

    public NBTTagInt_1_8_R3(int value) {
        this.tag = new NBTTagInt(value);
    }

    public net.minecraft.server.v1_8_R3.NBTTagInt getInternal() {
        return tag;
    }

    @Override
    public void init(int value) {
        tag = new net.minecraft.server.v1_8_R3.NBTTagInt(value);
    }

    @Override
    public int getInt() {
        return tag.e();
    }
}
