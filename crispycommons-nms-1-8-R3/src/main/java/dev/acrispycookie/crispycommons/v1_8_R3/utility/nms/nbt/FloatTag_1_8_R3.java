package dev.acrispycookie.crispycommons.v1_8_R3.utility.nms.nbt;

import dev.acrispycookie.crispycommons.utility.nms.nbt.FloatTag;
import net.minecraft.server.v1_8_R3.NBTTagFloat;
import org.jetbrains.annotations.NotNull;

public class FloatTag_1_8_R3 extends BaseTag_1_8_R3 implements FloatTag {

    private final NBTTagFloat tag;

    public FloatTag_1_8_R3(float value) {
        this.tag = new NBTTagFloat(value);
    }

    public @NotNull NBTTagFloat getInternal() {
        return tag;
    }

    @Override
    public float getFloat() {
        return tag.h();
    }
}
