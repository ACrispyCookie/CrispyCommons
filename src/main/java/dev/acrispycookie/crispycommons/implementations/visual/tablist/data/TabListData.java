package dev.acrispycookie.crispycommons.implementations.visual.tablist.data;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import org.bukkit.entity.Player;

import java.util.List;

public class TabListData implements VisualData {

    private List<TextElement<?>> header;
    private List<TextElement<?>> footer;

    public TabListData(List<TextElement<?>> header, List<TextElement<?>> footer) {
        this.header = header;
        this.footer = footer;
    }

    public List<TextElement<?>> getHeader() {
        return header;
    }

    public List<TextElement<?>> getFooter() {
        return footer;
    }

    public void setHeader(List<TextElement<?>> header) {
        this.header = header;
    }

    public void addHeaderLine(TextElement<?> text) {
        this.header.add(text);
    }

    public void addHeaderLine(int index, TextElement<?> text) {
        this.header.add(index, text);
    }

    public void removeHeaderLine(int index) {
        this.header.remove(index);
    }

    public void setFooter(List<TextElement<?>> footer) {
        this.footer = footer;
    }

    public void addFooterLine(TextElement<?> text) {
        this.footer.add(text);
    }

    public void addFooterLine(int index, TextElement<?> text) {
        this.footer.add(index, text);
    }

    public void removeFooterLine(int index) {
        this.footer.remove(index);
    }

    @Override
    public void assertReady(Player player) {
        if (header.isEmpty())
            throw new VisualNotReadyException("The tab list header was not set!");
        if (footer.isEmpty())
            throw new VisualNotReadyException("The tab list footer was not set!");
    }
}
