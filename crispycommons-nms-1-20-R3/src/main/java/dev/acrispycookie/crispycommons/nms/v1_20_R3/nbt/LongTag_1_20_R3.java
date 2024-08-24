package dev.acrispycookie.crispycommons.nms.v1_20_R3.nbt;

import dev.acrispycookie.crispycommons.nms.nbt.LongTag;
import net.minecraft.nbt.NBTTagLong;

public class LongTag_1_20_R3 extends BaseTag_1_20_R3 implements LongTag {

    private final NBTTagLong tag;

    public LongTag_1_20_R3(long value) {
        this.tag = NBTTagLong.a(value);
    }

    public NBTTagLong getInternal() {
        return tag;
    }

    @Override
    public long getLong() {
        return tag.f();
    }
}
