package dev.acrispycookie.crispycommons.holograms;

public class SimpleHologramText extends HologramText {

    public SimpleHologramText(String text) {
        this.currentText = text;
    }

    @Override
    public String getCurrentText() {
        return currentText;
    }

    public SimpleHologramText addLine(String line) {
        currentText = currentText + "\n" + line;
        return this;
    }
}
