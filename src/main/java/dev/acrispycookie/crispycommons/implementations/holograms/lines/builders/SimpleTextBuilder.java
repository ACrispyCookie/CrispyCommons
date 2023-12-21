package dev.acrispycookie.crispycommons.implementations.holograms.lines.builders;

import dev.acrispycookie.crispycommons.implementations.holograms.lines.CrispyHologramLine;
import dev.acrispycookie.crispycommons.implementations.holograms.lines.implementations.SimpleHologramLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleTextBuilder {

    private final ArrayList<String> lines;

    public SimpleTextBuilder() {
        this.lines = new ArrayList<>();
    }

    public SimpleTextBuilder addLine(String line) {
        this.lines.add(line);
        return this;
    }

    public SimpleTextBuilder addLines(String... lines) {
        this.lines.addAll(Arrays.asList(lines));
        return this;
    }

    public SimpleTextBuilder addLines(String line) {
        this.lines.addAll(Arrays.asList(line.split("\n")));
        return this;
    }

    public ArrayList<CrispyHologramLine> build() {
        ArrayList<CrispyHologramLine> hologramLines = new ArrayList<>();
        for (String line : lines) {
            hologramLines.add(new SimpleHologramLine(line));
        }
        return hologramLines;
    }
}
