package dev.acrispycookie.crispycommons.nms.v1_20_R3.nbt;

import dev.acrispycookie.crispycommons.nms.nbt.StringTag;
import net.minecraft.nbt.NBTTagString;

public class StringTag_1_20_R3 extends BaseTag_1_20_R3 implements StringTag {

    private final NBTTagString tag;

    public StringTag_1_20_R3(String value) {
        this.tag = NBTTagString.a(value);
    }

    public NBTTagString getInternal() {
        return tag;
    }

    @Override
    public String getString() {
        return tag.t_();
    }
}
