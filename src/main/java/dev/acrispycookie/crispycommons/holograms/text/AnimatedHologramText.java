package dev.acrispycookie.crispycommons.holograms.text;

import java.util.ArrayList;

public class AnimatedHologramText extends HologramText {

    private final ArrayList<SimpleHologramText> animation;
    private final int animationPeriod;
    private int animationPhase = 0;

    public AnimatedHologramText(ArrayList<SimpleHologramText> animation, int animationPeriod) {
        this.animation = animation;
        this.animationPeriod = animationPeriod;
    }

    @Override
    public String getCurrentText() {
        currentText = animation.get(animationPhase).getCurrentLines();
        animationPhase = (animationPhase + 1) == animation.size() ? 0 : animationPhase + 1;
        return String.join("\n", currentText);
    }

    @Override
    public ArrayList<String> getCurrentLines() {
        return animation.get(animationPhase).getCurrentLines();
    }

    public AnimatedHologramText addFrame(SimpleHologramText frame) {
        animation.add(frame);
        return this;
    }

    public AnimatedHologramText removeFrame(int frame) {
        animation.remove(frame);
        return this;
    }

    public AnimatedHologramText setFrame(int frame, SimpleHologramText text) {
        animation.set(frame, text);
        return this;
    }

    public int getAnimationPeriod() {
        return animationPeriod;
    }
}
