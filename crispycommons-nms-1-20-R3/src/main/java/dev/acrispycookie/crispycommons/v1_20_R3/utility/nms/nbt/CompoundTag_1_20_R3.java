package dev.acrispycookie.crispycommons.v1_20_R3.utility.nms.nbt;

import dev.acrispycookie.crispycommons.utility.nms.nbt.BaseTag;
import dev.acrispycookie.crispycommons.utility.nms.nbt.CompoundTag;
import net.minecraft.nbt.NBTTagCompound;

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
    public void set(String key, BaseTag value) {
        wrapped.a(key, ((BaseTag_1_20_R3) value).getInternal());
    }

    @Override
    public void setByte(String key, byte value) {
        wrapped.a(key, value);
    }

    @Override
    public void setShort(String key, short value) {
        wrapped.a(key, value);
    }

    @Override
    public void setInt(String key, int value) {
        wrapped.a(key, value);
    }

    @Override
    public void setLong(String key, long value) {
        wrapped.a(key, value);
    }

    @Override
    public void setFloat(String key, float value) {
        wrapped.a(key, value);
    }

    @Override
    public void setDouble(String key, double value) {
        wrapped.a(key, value);
    }

    @Override
    public void setString(String key, String value) {
        wrapped.a(key, value);
    }

    @Override
    public void setByteArray(String key, byte[] value) {
        wrapped.a(key, value);
    }

    @Override
    public void setIntArray(String key, int[] value) {
        wrapped.a(key, value);
    }

    @Override
    public void setBoolean(String key, boolean value) {
        wrapped.a(key, value);
    }

    @Override
    public byte getByte(String key) {
        return wrapped.f(key);
    }

    @Override
    public short getShort(String key) {
        return wrapped.g(key);
    }

    @Override
    public int getInt(String key) {
        return wrapped.h(key);
    }

    @Override
    public long getLong(String key) {
        return wrapped.i(key);
    }

    @Override
    public float getFloat(String key) {
        return wrapped.j(key);
    }

    @Override
    public double getDouble(String key) {
        return wrapped.k(key);
    }

    @Override
    public String getString(String key) {
        return wrapped.l(key);
    }

    @Override
    public byte[] getByteArray(String key) {
        return wrapped.m(key);
    }

    @Override
    public int[] getIntArray(String key) {
        return wrapped.n(key);
    }

    @Override
    public boolean getBoolean(String key) {
        return wrapped.q(key);
    }

    @Override
    public BaseTag get(String key) {
        BaseTag_1_20_R3 base = new BaseTag_1_20_R3();
        base.setBase(wrapped.c(key));
        return base;
    }

    @Override
    public boolean hasKey(String key) {
        return wrapped.e(key);
    }
}
