package dev.acrispycookie.crispycommons.nms.v1_20_R3.nbt;


import net.minecraft.nbt.NBTBase;

public class NBTBase_1_20_R3 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTBase {

    protected NBTBase base;

    public NBTBase_1_20_R3() {
    }

    public NBTBase_1_20_R3(NBTBase base) {
        this.base = base;
    }

    public void setBase(NBTBase base) {
        this.base = base;
    }

    public NBTBase getInternal() {
        return base;
    }
}
