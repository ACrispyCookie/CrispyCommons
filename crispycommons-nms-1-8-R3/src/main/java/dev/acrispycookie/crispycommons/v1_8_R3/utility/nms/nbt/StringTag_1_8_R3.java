package dev.acrispycookie.crispycommons.v1_8_R3.utility.nms.nbt;

import dev.acrispycookie.crispycommons.utility.nms.nbt.StringTag;
import net.minecraft.server.v1_8_R3.NBTTagString;
import org.jetbrains.annotations.NotNull;

public class StringTag_1_8_R3 extends BaseTag_1_8_R3 implements StringTag {

    private final NBTTagString tag;

    public StringTag_1_8_R3(String value) {
        this.tag = new NBTTagString(value);
    }

    public @NotNull NBTTagString getInternal() {
        return tag;
    }

    @Override
    public @NotNull String getString() {
        return tag.a_();
    }
}
