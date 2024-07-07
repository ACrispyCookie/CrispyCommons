package dev.acrispycookie.crispycommons.implementations.visuals.tablist.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalTextElement;

import java.util.List;

public class TablistData implements VisualData {

    private List<GlobalTextElement> header;
    private List<GlobalTextElement> footer;

    public TablistData(List<GlobalTextElement> header, List<GlobalTextElement> footer) {
        this.header = header;
        this.footer = footer;
    }

    public List<GlobalTextElement> getHeader() {
        return header;
    }

    public List<GlobalTextElement> getFooter() {
        return footer;
    }

    public void setHeader(List<GlobalTextElement> header) {
        this.header = header;
    }

    public void addHeaderLine(GlobalTextElement text) {
        this.header.add(text);
    }

    public void addHeaderLine(int index, GlobalTextElement text) {
        this.header.add(index, text);
    }

    public void removeHeaderLine(int index) {
        this.header.remove(index);
    }

    public void setFooter(List<GlobalTextElement> footer) {
        this.footer = footer;
    }

    public void addFooterLine(GlobalTextElement text) {
        this.footer.add(text);
    }

    public void addFooterLine(int index, GlobalTextElement text) {
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
