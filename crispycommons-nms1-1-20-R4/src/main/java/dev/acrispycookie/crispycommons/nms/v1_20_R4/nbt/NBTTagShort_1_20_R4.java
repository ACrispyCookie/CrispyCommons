package dev.acrispycookie.crispycommons.nms.v1_20_R4.nbt;

import net.minecraft.server.v1_8_R3.NBTTagShort;

public class NBTTagShort_1_20_R4 extends NBTBase_1_20_R4 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagShort {

    private net.minecraft.server.v1_8_R3.NBTTagShort tag;

    public NBTTagShort_1_20_R4(short value) {
        this.tag = new NBTTagShort(value);
    }

    public net.minecraft.server.v1_8_R3.NBTTagShort getInternal() {
        return tag;
    }

    @Override
    public void init(short value) {
        tag = new net.minecraft.server.v1_8_R3.NBTTagShort(value);
    }

    @Override
    public short getShort() {
        return tag.e();
    }
}
