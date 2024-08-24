package dev.acrispycookie.crispycommons.nms.v1_20_R3.nbt;

import dev.acrispycookie.crispycommons.nms.nbt.ShortTag;
import net.minecraft.nbt.NBTTagShort;

public class ShortTag_1_20_R3 extends BaseTag_1_20_R3 implements ShortTag {

    private final NBTTagShort tag;

    public ShortTag_1_20_R3(short value) {
        this.tag = NBTTagShort.a(value);
    }

    public NBTTagShort getInternal() {
        return tag;
    }

    @Override
    public short getShort() {
        return tag.h();
    }
}
