package dev.acrispycookie.crispycommons.nms.v1_20_R3.nbt;

import net.minecraft.nbt.NBTTagLong;

public class NBTTagLong_1_20_R3 extends NBTBase_1_20_R3 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagLong {

    private NBTTagLong tag;

    public NBTTagLong_1_20_R3(long value) {
        this.tag = NBTTagLong.a(value);
    }

    public NBTTagLong getInternal() {
        return tag;
    }

    @Override
    public void init(long value) {
        tag = NBTTagLong.a(value);
    }

    @Override
    public long getLong() {
        return tag.f();
    }
}
