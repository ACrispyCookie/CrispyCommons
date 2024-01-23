package dev.acrispycookie.crispycommons.utility.elements.implementations.text;

import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.function.Supplier;

public abstract class TextComponentElement extends AnimatedElement<TextComponent> {

    public TextComponentElement(ArrayList<? extends TextComponent> frames, int period, boolean async) {
        super(frames, period, async);
    }

    public TextComponentElement(Supplier<? extends TextComponent> supplier, int period, boolean async) {
        super(supplier, period, async);
    }
}
