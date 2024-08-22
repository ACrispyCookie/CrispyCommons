package dev.acrispycookie.crispycommons.implementations.visual.particle.data;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.particle.Effect;
import dev.acrispycookie.crispycommons.implementations.element.type.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.element.type.ParticleElement;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

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
    private ParticleElement<T, ?> element;

    /**
     * The location element that specifies where the particle effect will be displayed.
     */
    private GeneralElement<Location, ?> location;

    /**
     * Constructs a new {@code ParticleData} instance with the specified particle element and location.
     *
     * @param element the {@link ParticleElement} representing the particle effect.
     * @param location the {@link GeneralElement} representing the location of the particle effect.
     */
    public ParticleData(ParticleElement<T, ?> element, GeneralElement<Location, ?> location) {
        this.element = element;
        this.location = location;
    }

    /**
     * Retrieves the particle element that defines the particle effect.
     *
     * @return the {@link ParticleElement} associated with this data.
     */
    public ParticleElement<T, ?> getElement() {
        return element;
    }

    /**
     * Retrieves the location element that specifies where the particle effect will be displayed.
     *
     * @return the {@link GeneralElement} representing the location of the particle effect.
     */
    public GeneralElement<Location, ?> getLocation() {
        return location;
    }

    /**
     * Sets the particle element that defines the particle effect.
     *
     * @param element the new {@link ParticleElement} to set.
     */
    public void setElement(ParticleElement<T, ?> element) {
        this.element = element;
    }

    /**
     * Sets the location element that specifies where the particle effect will be displayed.
     *
     * @param location the new {@link GeneralElement} representing the location of the particle effect.
     */
    public void setLocation(GeneralElement<Location, ?> location) {
        this.location = location;
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
    public void assertReady(Player player) {
        if (element.getFromContext(OfflinePlayer.class, player) == null) {
            throw new VisualNotReadyException("The particle element was not set!");
        }
        if (location.getFromContext(OfflinePlayer.class, player) == null) {
            throw new VisualNotReadyException("The particle location was not set!");
        }
    }
}

