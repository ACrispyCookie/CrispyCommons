package dev.acrispycookie.crispycommons.nms.v1_8_R3.nbt;

import net.minecraft.server.v1_8_R3.NBTTagIntArray;

public class NBTTagIntArray_1_8_R3 extends NBTBase_1_8_R3 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagIntArray {

    private net.minecraft.server.v1_8_R3.NBTTagIntArray tag;

    public NBTTagIntArray_1_8_R3(int[] array) {
        this.tag = new NBTTagIntArray(array);
    }

    public net.minecraft.server.v1_8_R3.NBTTagIntArray getInternal() {
        return tag;
    }

    @Override
    public void init(int[] value) {
        tag = new net.minecraft.server.v1_8_R3.NBTTagIntArray(value);
    }

    @Override
    public int[] getIntArray() {
        return tag.c();
    }
}
