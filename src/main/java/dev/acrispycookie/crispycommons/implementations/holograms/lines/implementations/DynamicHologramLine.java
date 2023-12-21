package dev.acrispycookie.crispycommons.implementations.holograms.lines.implementations;

public abstract class DynamicHologramLine extends SimpleHologramLine {

    public abstract String getNew();

    public DynamicHologramLine(String initialLine) {
        super(initialLine);
    }

    @Override
    public void update() {
        this.line = getNew();
        super.update();
    }
}
