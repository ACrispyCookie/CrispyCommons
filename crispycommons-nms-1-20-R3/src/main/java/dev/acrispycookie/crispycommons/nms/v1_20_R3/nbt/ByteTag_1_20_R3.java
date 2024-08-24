package dev.acrispycookie.crispycommons.nms.v1_20_R3.nbt;

import dev.acrispycookie.crispycommons.nms.nbt.ByteTag;
import net.minecraft.nbt.NBTTagByte;

public class ByteTag_1_20_R3 extends BaseTag_1_20_R3 implements ByteTag {

    private final NBTTagByte tag;

    public ByteTag_1_20_R3(byte value) {
        this.tag = NBTTagByte.a(value);
    }

    public NBTTagByte getInternal() {
        return tag;
    }

    @Override
    public byte getByte() {
        return tag.i();
    }
}
