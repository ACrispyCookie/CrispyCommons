package dev.acrispycookie.crispycommons.implementations.visual.particle;

import dev.acrispycookie.crispycommons.api.visual.particle.CrispyParticle;
import dev.acrispycookie.crispycommons.api.particle.Effect;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visual.particle.data.ParticleData;
import dev.acrispycookie.crispycommons.implementations.element.type.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.element.type.ParticleElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import dev.acrispycookie.crispycommons.utility.visual.FieldUpdaterHelper;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * An abstract class that represents the base implementation of a particle effect in the CrispyVisual system.
 * <p>
 * {@code AbstractParticle} extends {@link AbstractVisual} and implements {@link CrispyParticle} to provide
 * core functionality for managing particle effects. This includes handling the lifecycle of the particle's
 * element and location, such as starting, stopping, and updating these components as needed.
 * </p>
 *
 * @param <T> the type of effect associated with this particle.
 */
public abstract class AbstractParticle<T extends Effect> extends AbstractVisual<ParticleData<T>> implements CrispyParticle<T> {

    /**
     * Constructs a new {@code AbstractParticle} instance with the specified parameters.
     *
     * @param data the {@link ParticleData} containing the visual and functional data for the particle.
     * @param receivers the set of players who will receive the particle effect.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the particle effect.
     * @param updateMode the update mode that determines how the particle effect is updated (e.g., per player, globally, etc.).
     * @param isPublic whether the particle effect should be visible to all players.
     */
    AbstractParticle(@NotNull ParticleData<T> data, @NotNull Set<? extends OfflinePlayer> receivers, @NotNull TimeToLiveElement<?> timeToLive, @NotNull UpdateMode updateMode, boolean isPublic) {
        super(data, receivers, timeToLive, updateMode, isPublic);
    }

    /**
     * Prepares the particle effect for display.
     * <p>
     * This method starts the element and location associated with the particle effect,
     * initializing them so they are ready to be shown to players.
     * </p>
     */
    @Override
    protected void prepareShow() {
        data.getElement().start();
        data.getLocation().start();
    }

    /**
     * Prepares the particle effect for hiding.
     * <p>
     * This method stops the element and location associated with the particle effect,
     * effectively hiding the particle effect from players.
     * </p>
     */
    @Override
    protected void prepareHide() {
        data.getElement().stop();
        data.getLocation().stop();
    }

    /**
     * Retrieves the particle element associated with this effect.
     *
     * @return the {@link ParticleElement} representing the particle effect.
     */
    @Override
    public @Nullable ParticleElement<T, ?> getElement() {
        return data.getElement() != null ? data.getElement().getElement() : null;
    }

    /**
     * Retrieves the location element associated with this particle effect.
     *
     * @return the {@link GeneralElement} representing the location of the particle effect.
     */
    @Override
    public @Nullable GeneralElement<Location, ?> getLocation() {
        return data.getLocation() != null ? data.getLocation().getElement() : null;
    }

    /**
     * Sets the particle element for this effect.
     * <p>
     * This method stops the current element, replaces it with the provided element, and then
     * starts the new element if the particle effect is currently being displayed.
     * </p>
     *
     * @param element the new {@link ParticleElement} to set.
     */
    @Override
    public void setElement(@NotNull ParticleElement<T, ?> element) {
        FieldUpdaterHelper.setNormalField(element, data::getElement, data::setElement, isAnyoneWatching(), this::update);
    }

    /**
     * Sets the location element for this particle effect.
     * <p>
     * This method stops the current location, replaces it with the provided location, and then
     * starts the new location if the particle effect is currently being displayed.
     * </p>
     *
     * @param location the new {@link GeneralElement} representing the location of the particle effect.
     */
    @Override
    public void setLocation(@NotNull GeneralElement<Location, ?> location) {
        FieldUpdaterHelper.setNormalField(location, data::getLocation, data::setLocation, isAnyoneWatching(), this::update);
    }
}

