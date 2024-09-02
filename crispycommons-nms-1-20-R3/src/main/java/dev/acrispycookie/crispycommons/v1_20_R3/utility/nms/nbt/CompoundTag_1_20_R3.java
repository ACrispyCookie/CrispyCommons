package dev.acrispycookie.crispycommons.v1_20_R3.utility.nms.nbt;

import dev.acrispycookie.crispycommons.utility.nms.nbt.BaseTag;
import dev.acrispycookie.crispycommons.utility.nms.nbt.CompoundTag;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CompoundTag_1_20_R3 extends BaseTag_1_20_R3 implements CompoundTag {

    private final NBTTagCompound wrapped;

    public CompoundTag_1_20_R3() {
        wrapped = new NBTTagCompound();
        setBase(wrapped);
    }

    public CompoundTag_1_20_R3(NBTTagCompound tag) {
        wrapped = tag;
        setBase(wrapped);
    }

    @Override
    public void set(@NotNull String key, @NotNull BaseTag value) {
        wrapped.a(key, ((BaseTag_1_20_R3) value).getInternal());
    }

    @Override
    public void setByte(@NotNull String key, byte value) {
        wrapped.a(key, value);
    }

    @Override
    public void setShort(@NotNull String key, short value) {
        wrapped.a(key, value);
    }

    @Override
    public void setInt(@NotNull String key, int value) {
        wrapped.a(key, value);
    }

    @Override
    public void setLong(@NotNull String key, long value) {
        wrapped.a(key, value);
    }

    @Override
    public void setFloat(@NotNull String key, float value) {
        wrapped.a(key, value);
    }

    @Override
    public void setDouble(@NotNull String key, double value) {
        wrapped.a(key, value);
    }

    @Override
    public void setString(@NotNull String key, @NotNull String value) {
        wrapped.a(key, value);
    }

    @Override
    public void setByteArray(@NotNull String key, byte[] value) {
        wrapped.a(key, value);
    }

    @Override
    public void setIntArray(@NotNull String key, int[] value) {
        wrapped.a(key, value);
    }

    @Override
    public void setBoolean(@NotNull String key, boolean value) {
        wrapped.a(key, value);
    }

    @Override
    public byte getByte(@NotNull String key) {
        return wrapped.f(key);
    }

    @Override
    public short getShort(@NotNull String key) {
        return wrapped.g(key);
    }

    @Override
    public int getInt(@NotNull String key) {
        return wrapped.h(key);
    }

    @Override
    public long getLong(@NotNull String key) {
        return wrapped.i(key);
    }

    @Override
    public float getFloat(@NotNull String key) {
        return wrapped.j(key);
    }

    @Override
    public double getDouble(@NotNull String key) {
        return wrapped.k(key);
    }

    @Override
    public @Nullable String getString(@NotNull String key) {
        return wrapped.l(key);
    }

    @Override
    public byte[] getByteArray(@NotNull String key) {
        return wrapped.m(key);
    }

    @Override
    public int[] getIntArray(@NotNull String key) {
        return wrapped.n(key);
    }

    @Override
    public boolean getBoolean(@NotNull String key) {
        return wrapped.q(key);
    }

    @Override
    public @NotNull BaseTag get(@NotNull String key) {
        BaseTag_1_20_R3 base = new BaseTag_1_20_R3();
        base.setBase(wrapped.c(key));
        return base;
    }

    @Override
    public boolean hasKey(@NotNull String key) {
        return wrapped.e(key);
    }
}
