package dev.acrispycookie.crispycommons.holograms;

import java.util.ArrayList;

public class AnimatedHologramText extends HologramText {

    private ArrayList<String> animation;
    private final int animationPeriod;
    private int animationPhase = 0;

    public AnimatedHologramText(ArrayList<String> animation, int animationPeriod) {
        this.animation = animation;
        this.animationPeriod = animationPeriod;
    }

    @Override
    public String getCurrentText() {
        currentText = animation.get(animationPhase);
        animationPhase = (animationPhase + 1) == animation.size() ? 0 : animationPhase + 1;
        return currentText;
    }

    public int getAnimationPeriod() {
        return animationPeriod;
    }
}
