package dev.acrispycookie.crispycommons.text;

import java.util.ArrayList;
import java.util.Arrays;

public class SimpleTextBuilder {

    private ArrayList<String> lines = new ArrayList<>();

    public SimpleTextBuilder(String firstLine) {
        lines.add(firstLine);
    }

    public SimpleTextBuilder addLine(String line) {
        lines.add(line);
        return this;
    }

    public SimpleTextBuilder addLines(String... lines) {
        this.lines.addAll(Arrays.asList(lines));
        return this;
    }

    public SimpleTextBuilder removeLine(int line) {
        lines.remove(line);
        return this;
    }

    public SimpleTextBuilder setLine(int line, String text) {
        lines.set(line, text);
        return this;
    }

    public SimpleCrispyText build() {
        return new SimpleCrispyText(lines);
    }
}
