package dev.acrispycookie.crispycommons.nms.v1_20_R3.nbt;

import net.minecraft.nbt.NBTTagByteArray;

public class NBTTagByteArray_1_20_R3 extends NBTBase_1_20_R3 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagByteArray {

    private NBTTagByteArray tag;

    public NBTTagByteArray_1_20_R3(byte[] array) {
        this.tag = new NBTTagByteArray(array);
    }

    public NBTTagByteArray getInternal() {
        return tag;
    }

    @Override
    public void init(byte[] value) {
        tag = new NBTTagByteArray(value);
        setBase(tag);
    }

    @Override
    public byte[] getByteArray() {
        return tag.e();
    }
}
