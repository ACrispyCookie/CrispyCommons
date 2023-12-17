package dev.acrispycookie.crispycommons.holograms.lines;

import java.util.ArrayList;
import java.util.List;

public class AnimatedHologramLine extends AbstractHologramLine {

    private ArrayList<String> animation;
    private final int period;
    private int currentIndex;

    public AnimatedHologramLine(int period, List<String> animation) {
        super(animation.get(0));
        this.period = period;
    }

    private void update() {
        this.line = animation.get(currentIndex + 1);
        currentIndex++;
    }


}
