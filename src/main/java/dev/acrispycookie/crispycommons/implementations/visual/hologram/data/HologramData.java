package dev.acrispycookie.crispycommons.implementations.visual.hologram.data;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import dev.acrispycookie.crispycommons.implementations.element.type.GeneralElement;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A data class representing the visual data required to display a hologram.
 * <p>
 * {@code HologramData} encapsulates the lines of text or elements that make up the hologram
 * and the location where the hologram will be displayed. This class also provides methods
 * to ensure the hologram data is ready for display.
 * </p>
 */
public class HologramData implements VisualData {

    /**
     * The list of dynamic elements representing the lines of the hologram.
     */
    private final List<DynamicElement<?, ?>> lines;

    /**
     * The location where the hologram will be displayed.
     */
    private GeneralElement<Location, ?> location;

    /**
     * Constructs a new {@code HologramData} instance with the specified lines and location.
     *
     * @param lines    the collection of dynamic elements that will be displayed as lines in the hologram.
     * @param location the {@link GeneralElement} representing the location of the hologram.
     */
    public HologramData(Collection<? extends DynamicElement<?, ?>> lines, GeneralElement<Location, ?> location) {
        this.lines = new ArrayList<>(lines);
        this.location = location;
    }

    /**
     * Retrieves the list of dynamic elements representing the lines of the hologram.
     *
     * @return the list of {@link DynamicElement} objects.
     */
    public List<DynamicElement<?, ?>> getLines() {
        return lines;
    }

    /**
     * Sets the lines of the hologram.
     * <p>
     * This method clears the existing lines and replaces them with the specified list of lines.
     * </p>
     *
     * @param lines the new list of dynamic elements to set as the lines of the hologram.
     */
    public void setLines(List<DynamicElement<?, ?>> lines) {
        this.lines.clear();
        this.lines.addAll(lines);
    }

    /**
     * Removes a line from the hologram at the specified index.
     *
     * @param index the index of the line to remove.
     */
    public void removeLine(int index) {
        this.lines.remove(index);
    }

    /**
     * Adds a line to the hologram at the specified index.
     *
     * @param index   the index at which to add the new line.
     * @param element the {@link DynamicElement} to add as a line.
     */
    public void addLine(int index, DynamicElement<?, ?> element) {
        this.lines.add(index, element);
    }

    /**
     * Retrieves the location where the hologram will be displayed.
     *
     * @return the {@link GeneralElement} representing the location of the hologram.
     */
    public GeneralElement<Location, ?> getLocation() {
        return location;
    }

    /**
     * Sets the location where the hologram will be displayed.
     *
     * @param location the new {@link GeneralElement} to set as the hologram's location.
     */
    public void setLocation(GeneralElement<Location, ?> location) {
        this.location = location;
    }

    /**
     * Asserts that the visual data is ready for display.
     * <p>
     * This method checks if the lines are set and if the location is properly defined
     * in the context of the specified player. If any of these conditions are not met,
     * a {@link VisualNotReadyException} is thrown.
     * </p>
     *
     * @param player the player for whom the visual data should be ready.
     * @throws VisualNotReadyException if the lines are empty or the location is not set.
     */
    @Override
    public void assertReady(Player player) {
        if (lines.isEmpty()) {
            throw new VisualNotReadyException("The hologram's lines were not set!");
        }
        if (location.getFromContext(OfflinePlayer.class, player) == null) {
            throw new VisualNotReadyException("The hologram's location was not set!");
        }
    }
}

