package dev.acrispycookie.crispycommons.utility.nms.nbt;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CompoundTag extends BaseTag {

    static @NotNull CompoundTag newInstance() {
        return VersionManager.createInstance(CompoundTag.class, new MappedVersions());
    }

    void set(@NotNull String key, @NotNull BaseTag value);
    void setByte(@NotNull String key, byte value);
    void setShort(@NotNull String key, short value);
    void setInt(@NotNull String key, int value);
    void setLong(@NotNull String key, long value);
    void setFloat(@NotNull String key, float value);
    void setDouble(@NotNull String key, double value);
    void setString(@NotNull String key, @NotNull String value);
    void setByteArray(@NotNull String key, byte[] value);
    void setIntArray(@NotNull String key, int[] value);
    void setBoolean(@NotNull String key, boolean value);
    byte getByte(@NotNull String key);
    short getShort(@NotNull String key);
    int getInt(@NotNull String key);
    long getLong(@NotNull String key);
    float getFloat(@NotNull String key);
    double getDouble(@NotNull String key);
    @Nullable String getString(@NotNull String key);
    byte[] getByteArray(@NotNull String key);
    int[] getIntArray(@NotNull String key);
    boolean getBoolean(@NotNull String key);
    @NotNull BaseTag get(@NotNull String key);
    boolean hasKey(@NotNull String key);
}
