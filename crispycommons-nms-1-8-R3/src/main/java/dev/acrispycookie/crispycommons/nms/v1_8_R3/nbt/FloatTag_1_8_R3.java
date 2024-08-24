package dev.acrispycookie.crispycommons.nms.v1_8_R3.nbt;

import dev.acrispycookie.crispycommons.nms.nbt.FloatTag;
import net.minecraft.server.v1_8_R3.NBTTagFloat;

public class FloatTag_1_8_R3 extends BaseTag_1_8_R3 implements FloatTag {

    private final NBTTagFloat tag;

    public FloatTag_1_8_R3(float value) {
        this.tag = new NBTTagFloat(value);
    }

    public NBTTagFloat getInternal() {
        return tag;
    }

    @Override
    public float getFloat() {
        return tag.h();
    }
}
