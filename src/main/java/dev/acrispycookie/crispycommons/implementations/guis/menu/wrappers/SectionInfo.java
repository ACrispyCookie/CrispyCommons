package dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers;

import dev.acrispycookie.crispycommons.api.guis.menu.sections.Section;

public class SectionInfo {

    private final int startIndex;
    private final int endIndex;
    private final int offset;
    private final Section section;

    public SectionInfo(int startIndex, int endIndex, int offset, Section section) {
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
