package dev.acrispycookie.crispycommons.implementations.gui.menu.data;

import dev.acrispycookie.crispycommons.api.gui.menu.section.Section;

/**
 * A data class that represents a specific section within a menu, including its indices and offset.
 * <p>
 * {@code SectionData} encapsulates the start and end indices, an offset value, and the {@link Section}
 * it represents. It is used to manage and organize different sections within a menu system.
 * </p>
 */
public class SectionData {

    /**
     * The starting index of the section within the menu.
     */
    private final int startIndex;

    /**
     * The ending index of the section within the menu.
     */
    private final int endIndex;

    /**
     * The offset value applied to this section.
     */
    private final int offset;

    /**
     * The {@link Section} that this data represents within the menu.
     */
    private final Section section;

    /**
     * Constructs a {@code SectionData} instance with the specified indices, offset, and section.
     *
     * @param startIndex the starting index of the section.
     * @param endIndex the ending index of the section.
     * @param offset the offset value for the section.
     * @param section the {@link Section} associated with this data.
     */
    public SectionData(int startIndex, int endIndex, int offset, Section section) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.offset = offset;
        this.section = section;
    }

    /**
     * Retrieves the {@link Section} associated with this data.
     *
     * @return the associated {@link Section}.
     */
    public Section getSection() {
        return section;
    }

    /**
     * Retrieves the starting index of the section.
     *
     * @return the starting index of the section.
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * Retrieves the ending index of the section.
     *
     * @return the ending index of the section.
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * Retrieves the offset value for the section.
     *
     * @return the offset value.
     */
    public int getOffset() {
        return offset;
    }
}

