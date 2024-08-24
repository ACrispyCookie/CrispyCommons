package dev.acrispycookie.crispycommons.nms.v1_8_R3.nbt;

import dev.acrispycookie.crispycommons.nms.nbt.IntArrayTag;
import net.minecraft.server.v1_8_R3.NBTTagIntArray;

public class IntArrayTag_1_8_R3 extends BaseTag_1_8_R3 implements IntArrayTag {

    private final NBTTagIntArray tag;

    public IntArrayTag_1_8_R3(int[] array) {
        this.tag = new NBTTagIntArray(array);
    }

    public NBTTagIntArray getInternal() {
        return tag;
    }

    @Override
    public int[] getIntArray() {
        return tag.c();
    }
}
