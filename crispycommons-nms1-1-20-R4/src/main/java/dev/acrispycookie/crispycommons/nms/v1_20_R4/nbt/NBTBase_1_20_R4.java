package dev.acrispycookie.crispycommons.nms.v1_20_R4.nbt;

import dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTBase;

public class NBTBase_1_20_R4 implements NBTBase {

    protected net.minecraft.server.v1_8_R3.NBTBase base;

    public NBTBase_1_20_R4() {
    }

    public NBTBase_1_20_R4(net.minecraft.server.v1_8_R3.NBTBase base) {
        this.base = base;
    }

    public void setBase(net.minecraft.server.v1_8_R3.NBTBase base) {
        this.base = base;
    }

    public net.minecraft.server.v1_8_R3.NBTBase getInternal() {
        return base;
    }
}
