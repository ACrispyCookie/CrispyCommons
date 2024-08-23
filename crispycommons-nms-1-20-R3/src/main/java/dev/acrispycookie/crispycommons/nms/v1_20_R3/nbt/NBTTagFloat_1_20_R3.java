package dev.acrispycookie.crispycommons.nms.v1_20_R3.nbt;

import net.minecraft.nbt.NBTTagFloat;

public class NBTTagFloat_1_20_R3 extends NBTBase_1_20_R3 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagFloat {

    private NBTTagFloat tag;

    public NBTTagFloat_1_20_R3(float value) {
        this.tag = NBTTagFloat.a(value);
    }

    public NBTTagFloat getInternal() {
        return tag;
    }

    @Override
    public void init(float value) {
        tag = NBTTagFloat.a(value);
    }

    @Override
    public float getFloat() {
        return tag.k();
    }
}
