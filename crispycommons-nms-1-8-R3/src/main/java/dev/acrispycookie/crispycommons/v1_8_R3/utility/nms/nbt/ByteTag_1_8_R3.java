package dev.acrispycookie.crispycommons.v1_8_R3.utility.nms.nbt;


import dev.acrispycookie.crispycommons.utility.nms.nbt.ByteTag;
import net.minecraft.server.v1_8_R3.NBTTagByte;
import org.jetbrains.annotations.NotNull;

public class ByteTag_1_8_R3 extends BaseTag_1_8_R3 implements ByteTag {

    private final NBTTagByte tag;

    public ByteTag_1_8_R3(byte value) {
        this.tag = new NBTTagByte(value);
    }

    public @NotNull NBTTagByte getInternal() {
        return tag;
    }

    @Override
    public byte getByte() {
        return tag.f();
    }
}
