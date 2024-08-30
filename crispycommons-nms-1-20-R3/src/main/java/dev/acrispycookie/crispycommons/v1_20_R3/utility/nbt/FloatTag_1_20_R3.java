package dev.acrispycookie.crispycommons.v1_20_R3.utility.nbt;

import dev.acrispycookie.crispycommons.utility.nbt.FloatTag;
import net.minecraft.nbt.NBTTagFloat;

public class FloatTag_1_20_R3 extends BaseTag_1_20_R3 implements FloatTag {

    private final NBTTagFloat tag;

    public FloatTag_1_20_R3(float value) {
        this.tag = NBTTagFloat.a(value);
    }

    public NBTTagFloat getInternal() {
        return tag;
    }

    @Override
    public float getFloat() {
        return tag.k();
    }
}
