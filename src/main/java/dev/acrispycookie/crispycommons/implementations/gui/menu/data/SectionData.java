package dev.acrispycookie.crispycommons.implementations.gui.menu.data;

import dev.acrispycookie.crispycommons.api.gui.menu.section.Section;

public class SectionData {

    private final int startIndex;
    private final int endIndex;
    private final int offset;
    private final Section section;

    public SectionData(int startIndex, int endIndex, int offset, Section section) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.offset = offset;
        this.section = section;
    }

    public Section getSection() {
        return section;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public int getOffset() {
        return offset;
    }
}
