package dev.acrispycookie.crispycommons.nms.v1_20_R3.nbt;

import dev.acrispycookie.crispycommons.nms.nbt.IntTag;
import net.minecraft.nbt.NBTTagInt;

public class IntTag_1_20_R3 extends BaseTag_1_20_R3 implements IntTag {

    private final NBTTagInt tag;

    public IntTag_1_20_R3(int value) {
        this.tag = NBTTagInt.a(value);
    }

    public NBTTagInt getInternal() {
        return tag;
    }

    @Override
    public int getInt() {
        return tag.g();
    }
}
