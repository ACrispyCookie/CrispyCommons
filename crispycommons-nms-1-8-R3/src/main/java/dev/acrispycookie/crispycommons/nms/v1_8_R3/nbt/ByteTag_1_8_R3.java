package dev.acrispycookie.crispycommons.nms.v1_8_R3.nbt;


import dev.acrispycookie.crispycommons.nms.nbt.ByteTag;
import net.minecraft.server.v1_8_R3.NBTTagByte;

public class ByteTag_1_8_R3 extends BaseTag_1_8_R3 implements ByteTag {

    private final NBTTagByte tag;

    public ByteTag_1_8_R3(byte value) {
        this.tag = new NBTTagByte(value);
    }

    public NBTTagByte getInternal() {
        return tag;
    }

    @Override
    public byte getByte() {
        return tag.f();
    }
}
