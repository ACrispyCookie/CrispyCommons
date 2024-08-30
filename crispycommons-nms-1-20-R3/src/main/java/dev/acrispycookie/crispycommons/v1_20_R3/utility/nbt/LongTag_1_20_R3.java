package dev.acrispycookie.crispycommons.v1_20_R3.utility.nbt;

import dev.acrispycookie.crispycommons.utility.nbt.LongTag;
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
