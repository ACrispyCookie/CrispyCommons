package dev.acrispycookie.crispycommons.v1_8_R3.utility.nms.nbt;

import dev.acrispycookie.crispycommons.utility.nms.nbt.*;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.jetbrains.annotations.NotNull;

public class ListTag_1_8_R3 extends BaseTag_1_8_R3 implements ListTag {

    private final NBTTagList tag;

    public @NotNull NBTTagList getInternal() {
        return tag;
    }

    public ListTag_1_8_R3() {
        tag = new NBTTagList();
    }

    @Override
    public void add(@NotNull BaseTag nbtbase) {
        tag.add(((BaseTag_1_8_R3) nbtbase).getInternal());
    }

    @Override
    public void add(int index, @NotNull BaseTag nbtbase) {
        tag.a(index, ((BaseTag_1_8_R3) nbtbase).getInternal());
    }

    @Override
    public @NotNull BaseTag remove(int index) {
        BaseTag_1_8_R3 base = new ListTag_1_8_R3();
        base.setBase(tag.a(index));
        return base;
    }

    @Override
    public @NotNull CompoundTag getCompound(int index) {
        return new CompoundTag_1_8_R3(tag.get(index));
    }

    @Override
    public @NotNull DoubleTag getDouble(int index) {
        return new DoubleTag_1_8_R3(tag.d(index));
    }

    @Override
    public @NotNull FloatTag getFloat(int index) {
        return new FloatTag_1_8_R3(tag.e(index));
    }

    @Override
    public @NotNull IntArrayTag getIntArray(int index) {
        return new IntArrayTag_1_8_R3(tag.c(index));
    }

    @Override
    public @NotNull StringTag getString(int index) {
        return new StringTag_1_8_R3(tag.getString(index));
    }

    @Override
    public @NotNull BaseTag get(int index) {
        BaseTag_1_8_R3 base = new ListTag_1_8_R3();
        base.setBase(tag.g(index));
        return base;
    }
}
