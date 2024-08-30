package dev.acrispycookie.crispycommons.v1_8_R3.utility.nbt;

import dev.acrispycookie.crispycommons.utility.nbt.StringTag;
import net.minecraft.server.v1_8_R3.NBTTagString;

public class StringTag_1_8_R3 extends BaseTag_1_8_R3 implements StringTag {

    private final NBTTagString tag;

    public StringTag_1_8_R3(String value) {
        this.tag = new NBTTagString(value);
    }

    public NBTTagString getInternal() {
        return tag;
    }

    @Override
    public String getString() {
        return tag.a_();
    }
}
