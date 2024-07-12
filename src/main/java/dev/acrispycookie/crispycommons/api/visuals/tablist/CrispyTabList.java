package dev.acrispycookie.crispycommons.api.visuals.tablist;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.SimpleTabList;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.wrappers.TabListData;

import java.util.ArrayList;
import java.util.List;

public interface CrispyTabList extends CrispyVisual {

    static TabListBuilder builder() {
        return new TabListBuilder();
    }
    void setHeader(List<TextElement<?>> element);
    void setFooter(List<TextElement<?>> element);
    List<TextElement<?>> getHeader();
    List<TextElement<?>> getFooter();

    class TabListBuilder extends AbstractVisualBuilder<CrispyTabList> {

        private final TabListData data = new TabListData(new ArrayList<>(), new ArrayList<>());

        public TabListBuilder addHeaderLine(TextElement<?> text) {
            text.setUpdate(() -> toBuild.update());
            this.data.addHeaderLine(text);
            return this;
        }

        public TabListBuilder addFooterLine(TextElement<?> text) {
            text.setUpdate(() -> toBuild.update());
            this.data.addFooterLine(text);
            return this;
        }

        @Override
        public CrispyTabList build() {
            toBuild = new SimpleTabList(data, receivers, timeToLive);
            return toBuild;
        }
    }
}
