package dev.acrispycookie.crispycommons.nms.v1_20_R3.nbt;

import net.minecraft.nbt.NBTTagString;

public class NBTTagString_1_20_R3 extends NBTBase_1_20_R3 implements dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagString {

    private NBTTagString tag;

    public NBTTagString_1_20_R3(String value) {
        this.tag = NBTTagString.a(value);
    }

    public NBTTagString getInternal() {
        return tag;
    }

    @Override
    public void init(String value) {
        tag = NBTTagString.a(value);
    }

    @Override
    public String getString() {
        return tag.t_();
    }
}
