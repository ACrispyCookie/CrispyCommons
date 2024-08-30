package dev.acrispycookie.crispycommons.v1_20_R3.utility.nms.nbt;

import dev.acrispycookie.crispycommons.utility.nms.nbt.DoubleTag;
import net.minecraft.nbt.NBTTagDouble;

public class DoubleTag_1_20_R3 extends BaseTag_1_20_R3 implements DoubleTag {

    private final NBTTagDouble handle;

    public DoubleTag_1_20_R3(double value) {
        this.handle = NBTTagDouble.a(value);
    }

    public NBTTagDouble getInternal() {
        return handle;
    }

    @Override
    public double getDouble() {
        return handle.j();
    }
}
