package dev.acrispycookie.crispycommons.nms.v1_20_R3.nbt;

import net.minecraft.nbt.NBTTagShort;

public class NBTTagShort_1_20_R3 extends NBTBase_1_20_R3 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagShort {

    private NBTTagShort tag;

    public NBTTagShort_1_20_R3(short value) {
        this.tag = NBTTagShort.a(value);
    }

    public NBTTagShort getInternal() {
        return tag;
    }

    @Override
    public void init(short value) {
        tag = NBTTagShort.a(value);
    }

    @Override
    public short getShort() {
        return tag.h();
    }
}
