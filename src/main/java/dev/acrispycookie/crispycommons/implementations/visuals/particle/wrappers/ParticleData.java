package dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.ParticleElement;
import dev.acrispycookie.crispycommons.api.wrappers.particle.Effect;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalGeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalParticleElement;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ParticleData<T extends Effect> implements VisualData {
    private ParticleElement<T> element;
    private GeneralElement<Location> location;

    public ParticleData(ParticleElement<T> element, GeneralElement<Location> location) {
        this.element = element;
        this.location = location;
    }

    public ParticleElement<T> getElement() {
        return element;
    }

    public GeneralElement<Location> getLocation() {
        return location;
    }

    public void setElement(ParticleElement<T> element) {
        this.element = element;
    }

    public void setLocation(GeneralElement<Location> location) {
        this.location = location;
    }

    @Override
    public void assertReady(Player player) {
        if (element == null || (element instanceof PersonalParticleElement<?> && ((PersonalParticleElement<T>) element).getRaw(player) == null))
            throw new VisualNotReadyException("The particle element was not set!");
        if (location == null || (location instanceof PersonalGeneralElement && ((PersonalGeneralElement<Location>) location).getRaw(player) == null))
            throw new VisualNotReadyException("The particle location was not set!");

    }
}
