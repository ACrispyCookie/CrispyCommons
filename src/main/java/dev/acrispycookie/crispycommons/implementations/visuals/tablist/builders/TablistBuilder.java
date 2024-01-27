package dev.acrispycookie.crispycommons.implementations.visuals.tablist.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.tablist.CrispyTablist;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.SimpleTablist;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.wrappers.TablistData;

import java.util.*;

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

    public TablistBuilder addHeaderLine(TextElement text) {
        text.setUpdate(() -> builtTablist.update());
        this.header.add(text);
        return this;
    }

    public TablistBuilder addFooterLine(TextElement text) {
        text.setUpdate(() -> builtTablist.update());
        this.footer.add(text);
        return this;
    }

    @Override
    public CrispyTablist build() {
        TablistData data = new TablistData(header, footer);
        builtTablist = new SimpleTablist(data, receivers);
        return builtTablist;
    }
}
