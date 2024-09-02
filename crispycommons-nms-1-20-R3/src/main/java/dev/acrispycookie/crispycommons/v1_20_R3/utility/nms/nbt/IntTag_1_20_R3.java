package dev.acrispycookie.crispycommons.v1_20_R3.utility.nms.nbt;

import dev.acrispycookie.crispycommons.utility.nms.nbt.IntTag;
import net.minecraft.nbt.NBTTagInt;
import org.jetbrains.annotations.NotNull;

public class IntTag_1_20_R3 extends BaseTag_1_20_R3 implements IntTag {

    private final NBTTagInt tag;

    public IntTag_1_20_R3(int value) {
        this.tag = NBTTagInt.a(value);
    }

    public @NotNull NBTTagInt getInternal() {
        return tag;
    }

    @Override
    public int getInt() {
        return tag.g();
    }
}
