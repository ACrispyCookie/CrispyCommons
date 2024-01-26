package dev.acrispycookie.crispycommons.implementations.visuals.tablist.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.tablist.CrispyTablist;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.SimpleTablist;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;

import java.util.*;
import java.util.function.Supplier;

public class SimpleTablistBuilder extends AbstractVisualBuilder<CrispyTablist> {

    private final List<TextElement> header = new ArrayList<>();
    private final List<TextElement> footer = new ArrayList<>();
    private SimpleTablist builtTablist;

    public SimpleTablistBuilder(List<TextElement> initialHeader, List<TextElement> initialFooter) {
        this.header.addAll(initialHeader);
        this.footer.addAll(initialFooter);
    }

    public SimpleTablistBuilder() {

    }

    public SimpleTablistBuilder addHeaderLine(String text) {
        this.header.add(new SimpleTextElement(text));
        return this;
    }

    public SimpleTablistBuilder addAnimatedHeaderLine(Collection<? extends String> frames, int period) {
        this.header.add(new TextElement(frames, period, false) {
            @Override
            protected void update() {
                builtTablist.update();
            }
        });
        return this;
    }

    public SimpleTablistBuilder addAnimatedHeaderLine(Supplier<String> supplier, int period) {
        this.header.add(new TextElement(supplier, period, false) {
            @Override
            protected void update() {
                builtTablist.update();
            }
        });
        return this;
    }

    public SimpleTablistBuilder addFooterLine(String text) {
        this.footer.add(new SimpleTextElement(text));
        return this;
    }

    public SimpleTablistBuilder addAnimatedFooterLine(Collection<? extends String> frames, int period) {
        this.footer.add(new TextElement(frames, period, false) {
            @Override
            protected void update() {
                builtTablist.update();
            }
        });
        return this;
    }

    public SimpleTablistBuilder addAnimatedFooterLine(Supplier<String> supplier, int period) {
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
        ArrayList<List<TextElement>> tabList = new ArrayList<>();
        tabList.add(header);
        tabList.add(footer);
        builtTablist = new SimpleTablist(tabList, receivers);
        return builtTablist;
    }
}
