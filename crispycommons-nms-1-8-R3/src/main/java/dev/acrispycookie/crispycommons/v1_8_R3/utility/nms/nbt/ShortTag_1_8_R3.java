package dev.acrispycookie.crispycommons.v1_8_R3.utility.nms.nbt;

import dev.acrispycookie.crispycommons.utility.nms.nbt.ShortTag;
import net.minecraft.server.v1_8_R3.NBTTagShort;
import org.jetbrains.annotations.NotNull;

public class ShortTag_1_8_R3 extends BaseTag_1_8_R3 implements ShortTag {

    private final NBTTagShort tag;

    public ShortTag_1_8_R3(short value) {
        this.tag = new NBTTagShort(value);
    }

    public @NotNull NBTTagShort getInternal() {
        return tag;
    }

    @Override
    public short getShort() {
        return tag.e();
    }
}
