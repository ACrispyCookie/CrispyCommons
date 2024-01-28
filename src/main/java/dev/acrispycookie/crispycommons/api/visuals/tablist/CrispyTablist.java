package dev.acrispycookie.crispycommons.api.visuals.tablist;

import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.types.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.SimpleTablist;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.wrappers.TablistData;

import java.util.ArrayList;
import java.util.List;

public interface CrispyTablist extends CrispyVisual {

    static TablistBuilder builder() {
        return new TablistBuilder();
    }
    void setHeader(List<TextElement> element);
    void setFooter(List<TextElement> element);
    List<TextElement> getHeader();
    List<TextElement> getFooter();

    class TablistBuilder extends AbstractVisualBuilder<CrispyTablist> {

        private SimpleTablist tablist;
        private final TablistData data = new TablistData(new ArrayList<>(), new ArrayList<>());

        public TablistBuilder addHeaderLine(TextElement text) {
            text.setUpdate(() -> tablist.update());
            this.data.addHeaderLine(text);
            return this;
        }

        public TablistBuilder addFooterLine(TextElement text) {
            text.setUpdate(() -> tablist.update());
            this.data.addFooterLine(text);
            return this;
        }

        @Override
        public CrispyTablist build() {
            tablist = new SimpleTablist(data, receivers, timeToLive);
            return tablist;
        }
    }
}
