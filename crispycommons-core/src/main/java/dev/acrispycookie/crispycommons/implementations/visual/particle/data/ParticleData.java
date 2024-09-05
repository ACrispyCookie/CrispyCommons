package dev.acrispycookie.crispycommons.implementations.visual.particle.data;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.particle.Effect;
import dev.acrispycookie.crispycommons.implementations.element.OwnedElement;
import dev.acrispycookie.crispycommons.implementations.element.type.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.element.type.ParticleElement;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A data class that encapsulates the visual data needed to render a particle effect in the CrispyVisual system.
 * <p>
 * {@code ParticleData} holds the particle element and its location, which are essential for displaying the particle effect.
 * It also provides methods to retrieve and set these components, ensuring they are ready before the effect is shown.
 * </p>
 *
 * @param <T> the type of effect associated with the particle element.
 */
public class ParticleData<T extends Effect> implements VisualData {

    /**
     * The particle element that defines the type and behavior of the particle effect.
     */
    private OwnedElement<ParticleElement<T, ?>> element;

    /**
     * The location element that specifies where the particle effect will be displayed.
     */
    private OwnedElement<GeneralElement<Location, ?>> location;

    /**
     * Constructs a new {@code ParticleData} instance with the specified particle element and location.
     *
     * @param element the {@link ParticleElement} representing the particle effect.
     * @param location the {@link GeneralElement} representing the location of the particle effect.
     */
    public ParticleData(@Nullable ParticleElement<T, ?> element, @Nullable GeneralElement<Location, ?> location) {
        this.element = new OwnedElement<>(element, this);
        this.location = new OwnedElement<>(location, this);
    }

    /**
     * Retrieves the particle element that defines the particle effect.
     *
     * @return the {@link ParticleElement} associated with this data.
     */
    public @NotNull OwnedElement<ParticleElement<T, ?>> getElement() {
        return element;
    }

    /**
     * Retrieves the location element that specifies where the particle effect will be displayed.
     *
     * @return the {@link GeneralElement} representing the location of the particle effect.
     */
    public @NotNull OwnedElement<GeneralElement<Location, ?>> getLocation() {
        return location;
    }

    /**
     * Sets the particle element that defines the particle effect.
     *
     * @param element the new {@link ParticleElement} to set.
     */
    public void setElement(@NotNull ParticleElement<T, ?> element) {
        this.element = new OwnedElement<>(element, this);
    }

    /**
     * Sets the location element that specifies where the particle effect will be displayed.
     *
     * @param location the new {@link GeneralElement} representing the location of the particle effect.
     */
    public void setLocation(@NotNull GeneralElement<Location, ?> location) {
        this.location = new OwnedElement<>(location, this);
    }

    /**
     * Asserts that the visual data is ready for display.
     * <p>
     * This method checks if the particle element and location can be retrieved from the context
     * of the specified player. If either of these elements is not set or cannot be retrieved,
     * a {@link VisualNotReadyException} is thrown.
     * </p>
     *
     * @param player the player for whom the visual data should be ready.
     * @throws VisualNotReadyException if the particle element or location is not set or cannot be retrieved.
     */
    @Override
    public void assertReady(@NotNull Player player) {
        if (element == null) {
            throw new VisualNotReadyException("The particle element was not set!");
        }
        if (location == null) {
            throw new VisualNotReadyException("The particle location was not set!");
        }
    }
}

