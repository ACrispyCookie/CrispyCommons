package dev.acrispycookie.crispycommons.v1_20_R3.utility.nms.nbt;

import dev.acrispycookie.crispycommons.utility.nms.nbt.LongTag;
import net.minecraft.nbt.NBTTagLong;
import org.jetbrains.annotations.NotNull;

public class LongTag_1_20_R3 extends BaseTag_1_20_R3 implements LongTag {

    private final NBTTagLong tag;

    public LongTag_1_20_R3(long value) {
        this.tag = NBTTagLong.a(value);
    }

    public @NotNull NBTTagLong getInternal() {
        return tag;
    }

    @Override
    public long getLong() {
        return tag.f();
    }
}
