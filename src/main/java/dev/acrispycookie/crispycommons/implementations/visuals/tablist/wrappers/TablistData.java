package dev.acrispycookie.crispycommons.implementations.visuals.tablist.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;

import java.util.List;

public class TablistData implements VisualData {

    private List<TextElement> header;
    private List<TextElement> footer;

    public TablistData(List<TextElement> header, List<TextElement> footer) {
        this.header = header;
        this.footer = footer;
    }

    public List<TextElement> getHeader() {
        return header;
    }

    public List<TextElement> getFooter() {
        return footer;
    }

    public void setHeader(List<TextElement> header) {
        this.header = header;
    }

    public void addHeaderLine(TextElement text) {
        this.header.add(text);
    }

    public void addHeaderLine(int index, TextElement text) {
        this.header.add(index, text);
    }

    public void removeHeaderLine(int index) {
        this.header.remove(index);
    }

    public void setFooter(List<TextElement> footer) {
        this.footer = footer;
    }

    public void addFooterLine(TextElement text) {
        this.footer.add(text);
    }

    public void addFooterLine(int index, TextElement text) {
        this.footer.add(index, text);
    }

    public void removeFooterLine(int index) {
        this.footer.remove(index);
    }

    @Override
    public void assertReady() {
        if (header == null)
            throw new VisualNotReadyException("The tablist header was not set!");
        if (footer == null)
            throw new VisualNotReadyException("The tablist footer was not set!");
    }
}
