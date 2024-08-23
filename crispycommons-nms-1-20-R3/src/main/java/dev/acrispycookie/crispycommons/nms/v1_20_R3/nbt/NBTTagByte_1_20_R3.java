package dev.acrispycookie.crispycommons.nms.v1_20_R3.nbt;

import net.minecraft.nbt.NBTTagByte;

public class NBTTagByte_1_20_R3 extends NBTBase_1_20_R3 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagByte {

    private NBTTagByte tag;

    public NBTTagByte_1_20_R3(byte value) {
        this.tag = NBTTagByte.a(value);
    }

    public NBTTagByte getInternal() {
        return tag;
    }

    @Override
    public void init(byte value) {
        tag = NBTTagByte.a(value);
        setBase(tag);
    }

    @Override
    public byte getByte() {
        return tag.i();
    }
}
