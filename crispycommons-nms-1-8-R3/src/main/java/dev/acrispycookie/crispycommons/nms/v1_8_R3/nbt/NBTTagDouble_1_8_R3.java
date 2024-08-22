package dev.acrispycookie.crispycommons.nms.v1_8_R3.nbt;

import net.minecraft.server.v1_8_R3.NBTTagDouble;

public class NBTTagDouble_1_8_R3 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagDouble {

    private net.minecraft.server.v1_8_R3.NBTTagDouble handle;

    public NBTTagDouble_1_8_R3(double value) {
        this.handle = new NBTTagDouble(value);
    }

    public net.minecraft.server.v1_8_R3.NBTTagDouble getInternal() {
        return handle;
    }

    @Override
    public void init(double value) {
        handle = new net.minecraft.server.v1_8_R3.NBTTagDouble(value);
    }

    @Override
    public double getDouble() {
        return handle.g();
    }
}
