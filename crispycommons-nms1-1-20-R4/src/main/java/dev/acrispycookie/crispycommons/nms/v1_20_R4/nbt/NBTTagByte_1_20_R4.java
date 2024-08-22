package dev.acrispycookie.crispycommons.nms.v1_20_R4.nbt;


import net.minecraft.server.v1_8_R3.NBTTagByte;

public class NBTTagByte_1_20_R4 extends NBTBase_1_20_R4 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagByte {

    private net.minecraft.server.v1_8_R3.NBTTagByte tag;

    public NBTTagByte_1_20_R4(byte value) {
        this.tag = new NBTTagByte(value);
    }

    public net.minecraft.server.v1_8_R3.NBTTagByte getInternal() {
        return tag;
    }

    @Override
    public void init(byte value) {
        tag = new net.minecraft.server.v1_8_R3.NBTTagByte(value);
        setBase(tag);
    }

    @Override
    public byte getByte() {
        return tag.f();
    }
}
