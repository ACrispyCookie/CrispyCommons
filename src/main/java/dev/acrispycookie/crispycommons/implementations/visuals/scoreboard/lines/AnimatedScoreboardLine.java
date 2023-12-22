package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;

public class AnimatedScoreboardLine extends AbstractScoreboardLine {

    private final AnimatedElement animatedElement;

    public AnimatedScoreboardLine(AnimatedElement animatedElement) {
        this.animatedElement = animatedElement;
    }

    @Override
    public String get() {
        return animatedElement.getCurrentFrame().get();
    }
}
