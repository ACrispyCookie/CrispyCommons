package dev.acrispycookie.crispycommons.nms.v1_20_R4.nbt;

import net.minecraft.server.v1_8_R3.NBTTagString;

public class NBTTagString_1_20_R4 extends NBTBase_1_20_R4 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagString {

    private net.minecraft.server.v1_8_R3.NBTTagString tag;

    public NBTTagString_1_20_R4(String value) {
        this.tag = new NBTTagString(value);
    }

    public net.minecraft.server.v1_8_R3.NBTTagString getInternal() {
        return tag;
    }

    @Override
    public void init(String value) {
        tag = new net.minecraft.server.v1_8_R3.NBTTagString(value);
    }

    @Override
    public String getString() {
        return tag.a_();
    }
}
