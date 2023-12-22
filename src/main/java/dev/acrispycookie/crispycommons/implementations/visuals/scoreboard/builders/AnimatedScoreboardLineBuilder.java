package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.builders;

import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.AnimatedScoreboardLine;
import dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines.SimpleScoreboardLine;

import java.util.ArrayList;

public class AnimatedScoreboardLineBuilder {

    private final ArrayList<String> frames = new ArrayList<>();

    public AnimatedScoreboardLineBuilder addFrames(ArrayList<String> frames) {
        this.frames.addAll(frames);
        return this;
    }

    public AnimatedScoreboardLineBuilder addFrame(String frame) {
        frames.add(frame);
        return this;
    }

    public AnimatedScoreboardLine build() {
        ArrayList<SimpleScoreboardLine> lines = new ArrayList<>();
        frames.forEach(frame -> lines.add(new SimpleScoreboardLine(frame)));
        return new AnimatedScoreboardLine(new AnimationBuilder<SimpleScoreboardLine>().addFrames(lines).build());
    }

}
