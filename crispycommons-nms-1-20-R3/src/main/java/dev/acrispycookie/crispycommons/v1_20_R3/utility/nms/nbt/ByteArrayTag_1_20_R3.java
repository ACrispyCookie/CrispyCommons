package dev.acrispycookie.crispycommons.v1_20_R3.utility.nms.nbt;

import dev.acrispycookie.crispycommons.utility.nms.nbt.ByteArrayTag;
import net.minecraft.nbt.NBTTagByteArray;
import org.jetbrains.annotations.NotNull;

public class ByteArrayTag_1_20_R3 extends BaseTag_1_20_R3 implements ByteArrayTag {

    private final NBTTagByteArray tag;

    public ByteArrayTag_1_20_R3(byte[] array) {
        this.tag = new NBTTagByteArray(array);
    }

    public @NotNull NBTTagByteArray getInternal() {
        return tag;
    }

    @Override
    public byte[] getByteArray() {
        return tag.e();
    }
}
