package dev.acrispycookie.crispycommons.utility.elements.implementations.text;

import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public abstract class TextElement extends AnimatedElement<String> {

    public TextElement(Collection<? extends String> frames, int period, boolean async) {
        super(new ArrayList<>(frames), period, async);
    }

    public TextElement(Supplier<String> supplier, int period, boolean async) {
        super(supplier, period, async);
    }
}
