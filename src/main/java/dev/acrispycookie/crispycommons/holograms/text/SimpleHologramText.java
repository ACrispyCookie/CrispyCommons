package dev.acrispycookie.crispycommons.holograms.text;

import java.util.ArrayList;
import java.util.Collections;

public class SimpleHologramText extends HologramText {

    public SimpleHologramText(String text) {
        ArrayList<String> lines = new ArrayList<>();
        Collections.addAll(lines, text.split("\n"));
        this.currentText = lines;
    }

    public SimpleHologramText(ArrayList<String> text) {
        this.currentText = text;
    }

    @Override
    public String getCurrentText() {
        return String.join("\n", currentText);
    }

    @Override
    public ArrayList<String> getCurrentLines() {
        return currentText;
    }

    public SimpleHologramText addLine(String line) {
        currentText.add(line);
        return this;
    }

    public SimpleHologramText removeLine(int line) {
        currentText.remove(line);
        return this;
    }

    public SimpleHologramText setLine(int line, String text) {
        currentText.set(line, text);
        return this;
    }
}
