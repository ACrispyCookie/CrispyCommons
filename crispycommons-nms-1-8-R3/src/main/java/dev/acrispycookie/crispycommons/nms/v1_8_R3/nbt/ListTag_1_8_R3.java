package dev.acrispycookie.crispycommons.nms.v1_8_R3.nbt;

import dev.acrispycookie.crispycommons.nms.nbt.*;
import net.minecraft.server.v1_8_R3.NBTTagList;

public class ListTag_1_8_R3 extends BaseTag_1_8_R3 implements ListTag {

    private final NBTTagList tag;

    public NBTTagList getInternal() {
        return tag;
    }

    public ListTag_1_8_R3() {
        tag = new NBTTagList();
    }

    @Override
    public void add(BaseTag nbtbase) {
        tag.add(((BaseTag_1_8_R3) nbtbase).getInternal());
    }

    @Override
    public void add(int index, BaseTag nbtbase) {
        tag.a(index, ((BaseTag_1_8_R3) nbtbase).getInternal());
    }

    @Override
    public BaseTag remove(int index) {
        BaseTag_1_8_R3 base = new ListTag_1_8_R3();
        base.setBase(tag.a(index));
        return base;
    }

    @Override
    public CompoundTag getCompound(int index) {
        return new CompoundTag_1_8_R3(tag.get(index));
    }

    @Override
    public DoubleTag getDouble(int index) {
        return new DoubleTag_1_8_R3(tag.d(index));
    }

    @Override
    public FloatTag getFloat(int index) {
        return new FloatTag_1_8_R3(tag.e(index));
    }

    @Override
    public IntArrayTag getIntArray(int index) {
        return new IntArrayTag_1_8_R3(tag.c(index));
    }

    @Override
    public StringTag getString(int index) {
        return new StringTag_1_8_R3(tag.getString(index));
    }

    @Override
    public BaseTag get(int index) {
        BaseTag_1_8_R3 base = new ListTag_1_8_R3();
        base.setBase(tag.g(index));
        return base;
    }
}
