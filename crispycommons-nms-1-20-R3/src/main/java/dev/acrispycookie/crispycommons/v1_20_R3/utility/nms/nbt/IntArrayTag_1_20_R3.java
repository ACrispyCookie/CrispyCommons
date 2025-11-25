package dev.acrispycookie.crispycommons.v1_20_R3.utility.nms.nbt;

import dev.acrispycookie.crispycommons.utility.nms.nbt.IntArrayTag;
import net.minecraft.nbt.NBTTagIntArray;
import org.jetbrains.annotations.NotNull;

public class IntArrayTag_1_20_R3 extends BaseTag_1_20_R3 implements IntArrayTag {

    private final NBTTagIntArray tag;

    public IntArrayTag_1_20_R3(int[] array) {
        this.tag = new NBTTagIntArray(array);
    }

    public @NotNull NBTTagIntArray getInternal() {
        return tag;
    }

    @Override
    public int[] getIntArray() {
        return tag.g();
    }
}
