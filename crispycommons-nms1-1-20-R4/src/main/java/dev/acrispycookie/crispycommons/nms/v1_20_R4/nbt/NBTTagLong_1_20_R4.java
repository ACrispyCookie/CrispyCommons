package dev.acrispycookie.crispycommons.nms.v1_20_R4.nbt;

import net.minecraft.server.v1_8_R3.NBTTagLong;

public class NBTTagLong_1_20_R4 extends NBTBase_1_20_R4 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagLong {

    private net.minecraft.server.v1_8_R3.NBTTagLong tag;

    public NBTTagLong_1_20_R4(long value) {
        this.tag = new NBTTagLong(value);
    }

    public net.minecraft.server.v1_8_R3.NBTTagLong getInternal() {
        return tag;
    }

    @Override
    public void init(long value) {
        tag = new net.minecraft.server.v1_8_R3.NBTTagLong(value);
    }

    @Override
    public long getLong() {
        return tag.d();
    }
}
