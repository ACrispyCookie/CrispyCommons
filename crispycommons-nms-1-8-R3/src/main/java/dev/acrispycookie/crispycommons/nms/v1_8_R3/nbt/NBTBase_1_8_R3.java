package dev.acrispycookie.crispycommons.nms.v1_8_R3.nbt;

import dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTBase;

public class NBTBase_1_8_R3 implements NBTBase {

    protected net.minecraft.server.v1_8_R3.NBTBase base;

    public NBTBase_1_8_R3() {
    }

    public NBTBase_1_8_R3(net.minecraft.server.v1_8_R3.NBTBase base) {
        this.base = base;
    }

    public void setBase(net.minecraft.server.v1_8_R3.NBTBase base) {
        this.base = base;
    }

    public net.minecraft.server.v1_8_R3.NBTBase getInternal() {
        return base;
    }
}
