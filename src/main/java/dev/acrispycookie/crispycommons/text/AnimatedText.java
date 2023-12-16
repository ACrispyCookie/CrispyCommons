package dev.acrispycookie.crispycommons.text;

import java.util.ArrayList;

public class AnimatedText extends CrispyText {

    private final ArrayList<SimpleCrispyText> animation;
    private final int animationPeriod;
    private int animationPhase = 0;

    public AnimatedText(ArrayList<SimpleCrispyText> animation, int animationPeriod) {
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

    public AnimatedText addFrame(String str) {
        animation.add(new SimpleCrispyText(str));
        return this;
    }

    public AnimatedText addFrame(SimpleCrispyText frame) {
        animation.add(frame);
        return this;
    }

    public AnimatedText removeFrame(int frame) {
        animation.remove(frame);
        return this;
    }

    public AnimatedText setFrame(int frame, SimpleCrispyText text) {
        animation.set(frame, text);
        return this;
    }

    public int getAnimationPeriod() {
        return animationPeriod;
    }
}
