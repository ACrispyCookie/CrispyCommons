package dev.acrispycookie.crispycommons.utility.elements.implementations.text;

import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;

import java.util.ArrayList;

public class AnimatedTextElement extends TextElement {

    private final AnimatedElement<String> animatedElement;

    public AnimatedTextElement(ArrayList<String> frames, int period) {
        super(frames.get(0));
        this.animatedElement = new AnimatedElement<>(frames, period);
    }

    public AnimatedElement<String> getAnimation() {
        return animatedElement;
    }

    @Override
    public String getElement() {
        return animatedElement.getElement();
    }
}
