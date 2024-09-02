package dev.acrispycookie.crispycommons.v1_8_R3.utility.nms.nbt;

import dev.acrispycookie.crispycommons.utility.nms.nbt.BaseTag;
import dev.acrispycookie.crispycommons.utility.nms.nbt.CompoundTag;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CompoundTag_1_8_R3 extends BaseTag_1_8_R3 implements CompoundTag {

    private final NBTTagCompound wrapped;

    public CompoundTag_1_8_R3() {
        wrapped = new NBTTagCompound();
        setBase(wrapped);
    }

    public CompoundTag_1_8_R3(@NotNull NBTTagCompound tag) {
        wrapped = tag;
        setBase(wrapped);
    }

                                 @Override
    public void set(@NotNull String key, @NotNull BaseTag value) {
        wrapped.set(key, ((BaseTag_1_8_R3) value).getInternal());
    }

    @Override
    public void setByte(@NotNull String key, byte value) {
        wrapped.setByte(key, value);
    }

    @Override
    public void setShort(@NotNull String key, short value) {
        wrapped.setShort(key, value);
    }

    @Override
    public void setInt(@NotNull String key, int value) {
        wrapped.setInt(key, value);
    }

    @Override
    public void setLong(@NotNull String key, long value) {
        wrapped.setLong(key, value);
    }

    @Override
    public void setFloat(@NotNull String key, float value) {
        wrapped.setFloat(key, value);
    }

    @Override
    public void setDouble(@NotNull String key, double value) {
        wrapped.setDouble(key, value);
    }

    @Override
    public void setString(@NotNull String key, @NotNull String value) {
        wrapped.setString(key, value);
    }

    @Override
    public void setByteArray(@NotNull String key, byte[] value) {
        wrapped.setByteArray(key, value);
    }

    @Override
    public void setIntArray(@NotNull String key, int[] value) {
        wrapped.setIntArray(key, value);
    }

    @Override
    public void setBoolean(@NotNull String key, boolean value) {
        wrapped.setBoolean(key, value);
    }

    @Override
    public byte getByte(@NotNull String key) {
        return wrapped.getByte(key);
    }

    @Override
    public short getShort(@NotNull String key) {
        return wrapped.getShort(key);
    }

    @Override
    public int getInt(@NotNull String key) {
        return wrapped.getInt(key);
    }

    @Override
    public long getLong(@NotNull String key) {
        return wrapped.getLong(key);
    }

    @Override
    public float getFloat(@NotNull String key) {
        return wrapped.getFloat(key);
    }

    @Override
    public double getDouble(@NotNull String key) {
        return wrapped.getDouble(key);
    }

    @Override
    public @Nullable String getString(@NotNull String key) {
        return wrapped.getString(key);
    }

    @Override
    public byte[] getByteArray(@NotNull String key) {
        return wrapped.getByteArray(key);
    }

    @Override
    public int[] getIntArray(@NotNull String key) {
        return wrapped.getIntArray(key);
    }

    @Override
    public boolean getBoolean(@NotNull String key) {
        return wrapped.getBoolean(key);
    }

    @Override
    public @NotNull BaseTag get(@NotNull String key) {
        BaseTag_1_8_R3 base = new BaseTag_1_8_R3();
        base.setBase(wrapped.get(key));
        return base;
    }

    @Override
    public boolean hasKey(@NotNull String key) {
        return wrapped.hasKey(key);
    }
}
