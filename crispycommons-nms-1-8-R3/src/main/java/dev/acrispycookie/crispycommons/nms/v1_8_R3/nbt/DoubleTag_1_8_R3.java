package dev.acrispycookie.crispycommons.nms.v1_8_R3.nbt;

import dev.acrispycookie.crispycommons.nms.nbt.DoubleTag;
import net.minecraft.server.v1_8_R3.NBTTagDouble;

public class DoubleTag_1_8_R3 implements DoubleTag {

    private final NBTTagDouble handle;

    public DoubleTag_1_8_R3(double value) {
        this.handle = new NBTTagDouble(value);
    }

    public NBTTagDouble getInternal() {
        return handle;
    }

    @Override
    public double getDouble() {
        return handle.g();
    }
}
