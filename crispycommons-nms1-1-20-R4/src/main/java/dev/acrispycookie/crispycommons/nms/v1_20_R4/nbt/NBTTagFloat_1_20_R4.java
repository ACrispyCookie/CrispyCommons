package dev.acrispycookie.crispycommons.nms.v1_20_R4.nbt;

import net.minecraft.server.v1_8_R3.NBTTagFloat;

public class NBTTagFloat_1_20_R4 extends NBTBase_1_20_R4 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagFloat {

    private net.minecraft.server.v1_8_R3.NBTTagFloat tag;

    public NBTTagFloat_1_20_R4(float value) {
        this.tag = new NBTTagFloat(value);
    }

    public net.minecraft.server.v1_8_R3.NBTTagFloat getInternal() {
        return tag;
    }

    @Override
    public void init(float value) {
        tag = new net.minecraft.server.v1_8_R3.NBTTagFloat(value);
    }

    @Override
    public float getFloat() {
        return tag.h();
    }
}
