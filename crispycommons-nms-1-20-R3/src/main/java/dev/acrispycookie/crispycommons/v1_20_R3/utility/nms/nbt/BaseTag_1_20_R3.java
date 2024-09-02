package dev.acrispycookie.crispycommons.v1_20_R3.utility.nms.nbt;


import dev.acrispycookie.crispycommons.utility.nms.nbt.BaseTag;
import net.minecraft.nbt.NBTBase;
import org.jetbrains.annotations.Nullable;

public class BaseTag_1_20_R3 implements BaseTag {

    protected NBTBase base;

    public BaseTag_1_20_R3() {
    }

    public BaseTag_1_20_R3(@Nullable NBTBase base) {
        this.base = base;
    }

    public void setBase(@Nullable NBTBase base) {
        this.base = base;
    }

    public @Nullable NBTBase getInternal() {
        return base;
    }
}
