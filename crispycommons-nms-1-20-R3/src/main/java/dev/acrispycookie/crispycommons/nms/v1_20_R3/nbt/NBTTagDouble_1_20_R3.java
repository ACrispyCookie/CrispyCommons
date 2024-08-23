package dev.acrispycookie.crispycommons.nms.v1_20_R3.nbt;

import net.minecraft.nbt.NBTTagDouble;

public class NBTTagDouble_1_20_R3 extends NBTBase_1_20_R3 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagDouble {

    private NBTTagDouble handle;

    public NBTTagDouble_1_20_R3(double value) {
        this.handle = NBTTagDouble.a(value);
    }

    public NBTTagDouble getInternal() {
        return handle;
    }

    @Override
    public void init(double value) {
        handle = NBTTagDouble.a(value);
    }

    @Override
    public double getDouble() {
        return handle.j();
    }
}
