package dev.acrispycookie.crispycommons.nms.v1_20_R3.nbt;

import net.minecraft.nbt.NBTTagIntArray;

public class NBTTagIntArray_1_20_R3 extends NBTBase_1_20_R3 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagIntArray {

    private NBTTagIntArray tag;

    public NBTTagIntArray_1_20_R3(int[] array) {
        this.tag = new NBTTagIntArray(array);
    }

    public NBTTagIntArray getInternal() {
        return tag;
    }

    @Override
    public void init(int[] value) {
        tag = new NBTTagIntArray(value);
    }

    @Override
    public int[] getIntArray() {
        return tag.g();
    }
}
