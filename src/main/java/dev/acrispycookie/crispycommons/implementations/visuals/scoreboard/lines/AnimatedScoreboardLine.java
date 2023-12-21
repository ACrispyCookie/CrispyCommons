package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

import dev.acrispycookie.crispycommons.utility.animation.Animation;

public class AnimatedScoreboardLine extends AbstractScoreboardLine {

    private final Animation<SimpleScoreboardLine> animation;

    public AnimatedScoreboardLine(Animation<SimpleScoreboardLine> animation) {
        this.animation = animation;
    }

    @Override
    public String get() {
        return animation.getCurrentFrame().get();
    }
}
