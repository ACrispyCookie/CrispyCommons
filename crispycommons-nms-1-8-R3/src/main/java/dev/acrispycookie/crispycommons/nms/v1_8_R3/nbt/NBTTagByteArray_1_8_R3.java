package dev.acrispycookie.crispycommons.nms.v1_8_R3.nbt;

import net.minecraft.server.v1_8_R3.NBTTagByteArray;

public class NBTTagByteArray_1_8_R3 extends NBTBase_1_8_R3 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagByteArray {

    private net.minecraft.server.v1_8_R3.NBTTagByteArray tag;

    public NBTTagByteArray_1_8_R3(byte[] array) {
        this.tag = new NBTTagByteArray(array);
    }

    public net.minecraft.server.v1_8_R3.NBTTagByteArray getInternal() {
        return tag;
    }

    @Override
    public void init(byte[] value) {
        tag = new net.minecraft.server.v1_8_R3.NBTTagByteArray(value);
        setBase(tag);
    }

    @Override
    public byte[] getByteArray() {
        return tag.c();
    }
}
