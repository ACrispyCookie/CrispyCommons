package dev.acrispycookie.crispycommons.v1_20_R3.utility.nbt;

import dev.acrispycookie.crispycommons.utility.nbt.*;
import net.minecraft.nbt.NBTTagList;

public class ListTag_1_20_R3 extends BaseTag_1_20_R3 implements ListTag {

    private final NBTTagList tag;

    public NBTTagList getInternal() {
        return tag;
    }

    public ListTag_1_20_R3() {
        tag = new NBTTagList();
    }

    @Override
    public void add(BaseTag nbtbase) {
        tag.add(((BaseTag_1_20_R3) nbtbase).getInternal());
    }

    @Override
    public void add(int index, BaseTag nbtbase) {
        tag.c(index, ((BaseTag_1_20_R3) nbtbase).getInternal());
    }

    @Override
    public BaseTag remove(int index) {
        BaseTag_1_20_R3 base = new ListTag_1_20_R3();
        base.setBase(tag.c(index));
        return base;
    }

    @Override
    public CompoundTag getCompound(int index) {
        return new CompoundTag_1_20_R3(tag.a(index));
    }

    @Override
    public DoubleTag getDouble(int index) {
        return new DoubleTag_1_20_R3(tag.h(index));
    }

    @Override
    public FloatTag getFloat(int index) {
        return new FloatTag_1_20_R3(tag.i(index));
    }

    @Override
    public IntArrayTag getIntArray(int index) {
        return new IntArrayTag_1_20_R3(tag.f(index));
    }

    @Override
    public StringTag getString(int index) {
        return new StringTag_1_20_R3(tag.j(index));
    }

    @Override
    public BaseTag get(int index) {
        BaseTag_1_20_R3 base = new ListTag_1_20_R3();
        base.setBase(tag.k(index));
        return base;
    }
}
