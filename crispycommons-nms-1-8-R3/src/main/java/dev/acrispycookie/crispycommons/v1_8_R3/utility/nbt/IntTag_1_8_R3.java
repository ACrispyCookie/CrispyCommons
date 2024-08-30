package dev.acrispycookie.crispycommons.v1_8_R3.utility.nbt;

import dev.acrispycookie.crispycommons.utility.nbt.IntTag;
import net.minecraft.server.v1_8_R3.NBTTagInt;

public class IntTag_1_8_R3 extends BaseTag_1_8_R3 implements IntTag {

    private final NBTTagInt tag;

    public IntTag_1_8_R3(int value) {
        this.tag = new NBTTagInt(value);
    }

    public NBTTagInt getInternal() {
        return tag;
    }

    @Override
    public int getInt() {
        return tag.e();
    }
}
