package dev.acrispycookie.crispycommons.implementations.visuals.tablist.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.tablist.CrispyTablist;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.SimpleTablist;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.wrappers.TablistData;

import java.util.*;
import java.util.function.Supplier;

public class TablistBuilder extends AbstractVisualBuilder<CrispyTablist> {

    private final List<TextElement> header = new ArrayList<>();
    private final List<TextElement> footer = new ArrayList<>();
    private SimpleTablist builtTablist;

    public TablistBuilder(List<TextElement> initialHeader, List<TextElement> initialFooter) {
        this.header.addAll(initialHeader);
        this.footer.addAll(initialFooter);
    }

    public TablistBuilder() {

    }

    public TablistBuilder addHeaderLine(String text) {
        this.header.add(new SimpleTextElement(text));
        return this;
    }

    public TablistBuilder addAnimatedHeaderLine(Collection<? extends String> frames, int period) {
        this.header.add(new TextElement(frames, period, false) {
            @Override
            protected void update() {
                builtTablist.update();
            }
        });
        return this;
    }

    public TablistBuilder addAnimatedHeaderLine(Supplier<String> supplier, int period) {
        this.header.add(new TextElement(supplier, period, false) {
            @Override
            protected void update() {
                builtTablist.update();
            }
        });
        return this;
    }

    public TablistBuilder addFooterLine(String text) {
        this.footer.add(new SimpleTextElement(text));
        return this;
    }

    public TablistBuilder addAnimatedFooterLine(Collection<? extends String> frames, int period) {
        this.footer.add(new TextElement(frames, period, false) {
            @Override
            protected void update() {
                builtTablist.update();
            }
        });
        return this;
    }

    public TablistBuilder addAnimatedFooterLine(Supplier<String> supplier, int period) {
        this.footer.add(new TextElement(supplier, period, false) {
            @Override
            protected void update() {
                builtTablist.update();
            }
        });
        return this;
    }

    @Override
    public CrispyTablist build() {
        TablistData data = new TablistData(header, footer);
        builtTablist = new SimpleTablist(data, receivers);
        return builtTablist;
    }
}
