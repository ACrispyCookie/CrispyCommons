package dev.acrispycookie.crispycommons.utility.elements.implementations.text;

import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;

import java.util.ArrayList;

public abstract class TextElement extends AnimatedElement<String> {

    public TextElement(ArrayList<String> frames, int period) {
        super(frames, period);
    }
}
