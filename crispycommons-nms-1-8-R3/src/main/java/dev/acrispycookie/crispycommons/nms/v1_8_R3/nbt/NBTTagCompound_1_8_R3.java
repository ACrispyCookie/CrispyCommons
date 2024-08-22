package dev.acrispycookie.crispycommons.nms.v1_8_R3.nbt;

import dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTBase;
import dev.acrispycookie.crispycommons.nms.wrappers.nbt.NBTTagCompound;

public class NBTTagCompound_1_8_R3 extends NBTBase_1_8_R3 implements NBTTagCompound {

    private final net.minecraft.server.v1_8_R3.NBTTagCompound wrapped;

    public NBTTagCompound_1_8_R3() {
        wrapped = new net.minecraft.server.v1_8_R3.NBTTagCompound();
        setBase(wrapped);
    }

    public NBTTagCompound_1_8_R3(net.minecraft.server.v1_8_R3.NBTTagCompound tag) {
        wrapped = tag;
        setBase(wrapped);
    }

                                 @Override
    public void set(String key, NBTBase value) {
        wrapped.set(key, ((NBTBase_1_8_R3) value).getInternal());
    }

    @Override
    public void setByte(String key, byte value) {
        wrapped.setByte(key, value);
    }

    @Override
    public void setShort(String key, short value) {
        wrapped.setShort(key, value);
    }

    @Override
    public void setInt(String key, int value) {
        wrapped.setInt(key, value);
    }

    @Override
    public void setLong(String key, long value) {
        wrapped.setLong(key, value);
    }

    @Override
    public void setFloat(String key, float value) {
        wrapped.setFloat(key, value);
    }

    @Override
    public void setDouble(String key, double value) {
        wrapped.setDouble(key, value);
    }

    @Override
    public void setString(String key, String value) {
        wrapped.setString(key, value);
    }

    @Override
    public void setByteArray(String key, byte[] value) {
        wrapped.setByteArray(key, value);
    }

    @Override
    public void setIntArray(String key, int[] value) {
        wrapped.setIntArray(key, value);
    }

    @Override
    public void setBoolean(String key, boolean value) {
        wrapped.setBoolean(key, value);
    }

    @Override
    public short getShort(String key) {
        return wrapped.getShort(key);
    }

    @Override
    public int getInt(String key) {
        return wrapped.getInt(key);
    }

    @Override
    public long getLong(String key) {
        return wrapped.getLong(key);
    }

    @Override
    public float getFloat(String key) {
        return wrapped.getFloat(key);
    }

    @Override
    public double getDouble(String key) {
        return wrapped.getDouble(key);
    }

    @Override
    public String getString(String key) {
        return wrapped.getString(key);
    }

    @Override
    public byte[] getByteArray(String key) {
        return wrapped.getByteArray(key);
    }

    @Override
    public int[] getIntArray(String key) {
        return wrapped.getIntArray(key);
    }

    @Override
    public boolean getBoolean(String key) {
        return wrapped.getBoolean(key);
    }

    @Override
    public NBTBase get(String key) {
        NBTBase_1_8_R3 base = new NBTBase_1_8_R3();
        base.setBase(wrapped.get(key));
        return base;
    }

    @Override
    public boolean hasKey(String key) {
        return wrapped.hasKey(key);
    }
}
