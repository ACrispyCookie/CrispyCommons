package dev.acrispycookie.crispycommons.holograms.lines;

public class AbstractHologramLine implements CrispyHologramLine {

    protected String line;

    public AbstractHologramLine(String initialLine) {
        this.line = initialLine;
    }

    @Override
    public String getCurrent() {
        return line;
    }
}
