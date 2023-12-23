package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.TextElement;

public class AnimatedScoreboardLine extends AbstractScoreboardLine {

    private final AnimatedElement<TextElement> animatedElement;

    public AnimatedScoreboardLine(AnimatedElement<TextElement> animatedElement) {
        this.animatedElement = animatedElement;
    }

    @Override
    public String get() {
        return animatedElement.getElement().getElement();
    }
}
