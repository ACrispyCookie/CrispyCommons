package dev.acrispycookie.crispycommons.implementations.visuals.scoreboard.lines;

public abstract class AbstractScoreboardLine implements ScoreboardLine {

    protected String line;

    @Override
    public String get() {
        return line;
    }
}
