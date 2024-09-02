package dev.acrispycookie.crispycommons.api.visual.hologram;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visual.hologram.SimpleHologram;
import dev.acrispycookie.crispycommons.implementations.visual.hologram.data.HologramData;
import dev.acrispycookie.crispycommons.implementations.element.type.GeneralElement;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents a hologram visual element in the game, extending the {@link CrispyVisual} interface.
 * <p>
 * The {@code CrispyHologram} interface allows for displaying and managing holograms with dynamic lines
 * and a specified location. It provides methods to add, remove, and manipulate lines of text or other
 * dynamic elements, as well as a builder for creating instances of holograms with customizable properties.
 * </p>
 */
public interface CrispyHologram extends CrispyVisual {

    /**
     * Creates a new instance of {@link HologramBuilder}, which can be used to build a {@code CrispyHologram}.
     *
     * @return a new {@link HologramBuilder} instance.
     */
    static @NotNull HologramBuilder builder() {
        return new HologramBuilder();
    }

    /**
     * Adds a new line to the hologram.
     *
     * @param line the {@link DynamicElement} to add as a line in the hologram.
     */
    void addLine(@NotNull DynamicElement<?, ?> line);

    /**
     * Adds a new line to the hologram at the specified index.
     *
     * @param index the index at which to add the line.
     * @param line the {@link DynamicElement} to add as a line in the hologram.
     */
    void addLine(int index, @NotNull DynamicElement<?, ?> line);

    /**
     * Removes a line from the hologram at the specified index.
     *
     * @param index the index of the line to remove.
     */
    void removeLine(int index);

    /**
     * Sets the lines of the hologram to the specified collection.
     *
     * @param lines the collection of {@link DynamicElement} instances to set as the hologram's lines.
     */
    void setLines(@NotNull Collection<? extends DynamicElement<?, ?>> lines);

    /**
     * Sets the location of the hologram.
     *
     * @param location the {@link GeneralElement} representing the hologram's location.
     */
    void setLocation(@NotNull GeneralElement<Location, ?> location);

    /**
     * Retrieves the list of lines currently displayed in the hologram.
     *
     * @return a {@link List} of {@link DynamicElement} instances representing the lines.
     */
    @NotNull List<DynamicElement<?, ?>> getLines();

    /**
     * Retrieves the location of the hologram.
     *
     * @return the {@link GeneralElement} representing the hologram's location.
     */
    @NotNull GeneralElement<Location, ?> getLocation();

    /**
     * Updates the location of the hologram.
     */
    void updateLocation();

    /**
     * Updates a specific line of the hologram.
     *
     * @param index the index of the line to update.
     */
    void updateLine(int index);

    /**
     * A builder class for constructing instances of {@link CrispyHologram}.
     * <p>
     * The {@code HologramBuilder} extends {@link AbstractVisualBuilder} and allows for the customization
     * of holograms, including setting the location, adding lines, specifying players who can view the hologram,
     * and other properties.
     * </p>
     */
    class HologramBuilder extends AbstractVisualBuilder<CrispyHologram> {

        private final HologramData data = new HologramData(new ArrayList<>(), null);

        /**
         * Sets the location of the hologram.
         * <p>
         * The location element updates the hologram whenever it is updated.
         * </p>
         *
         * @param location the {@link GeneralElement} representing the hologram's location.
         * @return this {@code HologramBuilder} instance for method chaining.
         */
        public @NotNull HologramBuilder setLocation(@NotNull GeneralElement<Location, ?> location) {
            this.data.setLocation(location);
            location.setUpdate(() -> toBuild.updateLocation());
            return this;
        }

        /**
         * Adds a new line to the hologram.
         * <p>
         * The line element updates the hologram whenever it is updated.
         * </p>
         *
         * @param element the {@link DynamicElement} to add as a line in the hologram.
         * @return this {@code HologramBuilder} instance for method chaining.
         */
        public @NotNull HologramBuilder addLine(@NotNull DynamicElement<?, ?> element) {
            this.data.getLines().add(element);
            int index = data.getLines().size() - 1;
            element.setUpdate(() -> toBuild.updateLine(index));
            return this;
        }

        /**
         * Builds and returns the configured {@link CrispyHologram} instance.
         *
         * @return the constructed {@link CrispyHologram} instance.
         */
        public @NotNull CrispyHologram build() {
            toBuild = new SimpleHologram(data, receivers, timeToLive, isPublic);
            return toBuild;
        }
    }
}

