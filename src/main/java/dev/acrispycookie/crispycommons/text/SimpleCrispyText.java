package dev.acrispycookie.crispycommons.text;

import java.util.ArrayList;
import java.util.Collections;

public class SimpleCrispyText extends CrispyText {

    public SimpleCrispyText(String text) {
        ArrayList<String> lines = new ArrayList<>();
        Collections.addAll(lines, text.split("\n"));
        this.currentText = lines;
    }

    public SimpleCrispyText(ArrayList<String> text) {
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

    public SimpleCrispyText addLine(String line) {
        currentText.add(line);
        return this;
    }

    public SimpleCrispyText removeLine(int line) {
        currentText.remove(line);
        return this;
    }

    public SimpleCrispyText setLine(int line, String text) {
        currentText.set(line, text);
        return this;
    }
}
