package dev.acrispycookie.crispycommons.utility.elements.implementations.text;

import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;

import java.util.ArrayList;
import java.util.Collection;

public abstract class TextElement extends AnimatedElement<String> {

    public TextElement(Collection<? extends String> frames, int period) {
        super(new ArrayList<>(frames), period);
    }
}
