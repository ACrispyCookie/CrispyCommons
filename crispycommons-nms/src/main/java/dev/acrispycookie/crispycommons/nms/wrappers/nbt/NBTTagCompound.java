package dev.acrispycookie.crispycommons.nms.wrappers.nbt;

import dev.acrispycookie.crispycommons.VersionManager;

public interface NBTTagCompound extends NBTBase {

    static NBTTagCompound newInstance() {
        return VersionManager.get().createInstance(NBTTagCompound.class);
    }

    void set(String key, NBTBase value);
    void setByte(String key, byte value);
    void setShort(String key, short value);
    void setInt(String key, int value);
    void setLong(String key, long value);
    void setFloat(String key, float value);
    void setDouble(String key, double value);
    void setString(String key, String value);
    void setByteArray(String key, byte[] value);
    void setIntArray(String key, int[] value);
    void setBoolean(String key, boolean value);
    short getShort(String key);
    int getInt(String key);
    long getLong(String key);
    float getFloat(String key);
    double getDouble(String key);
    String getString(String key);
    byte[] getByteArray(String key);
    int[] getIntArray(String key);
    boolean getBoolean(String key);
    NBTBase get(String key);
    boolean hasKey(String key);
}
