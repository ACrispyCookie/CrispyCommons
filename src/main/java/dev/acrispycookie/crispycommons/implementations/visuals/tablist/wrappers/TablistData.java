package dev.acrispycookie.crispycommons.implementations.visuals.tablist.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;

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

    public void setFooter(List<TextElement> footer) {
        this.footer = footer;
    }
}
