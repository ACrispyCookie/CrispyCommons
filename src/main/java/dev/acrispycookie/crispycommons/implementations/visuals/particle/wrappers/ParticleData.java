package dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.ParticleElement;
import dev.acrispycookie.crispycommons.api.wrappers.particle.Effect;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class ParticleData<T extends Effect> implements VisualData {
    private ParticleElement<T, ?> element;
    private GeneralElement<Location, ?> location;

    public ParticleData(ParticleElement<T, ?> element, GeneralElement<Location, ?> location) {
        this.element = element;
        this.location = location;
    }

    public ParticleElement<T, ?> getElement() {
        return element;
    }

    public GeneralElement<Location, ?> getLocation() {
        return location;
    }

    public void setElement(ParticleElement<T, ?> element) {
        this.element = element;
    }

    public void setLocation(GeneralElement<Location, ?> location) {
        this.location = location;
    }

    @Override
    public void assertReady(Player player) {
        if (element.getFromContext(OfflinePlayer.class, player) == null)
            throw new VisualNotReadyException("The particle element was not set!");
        if (location.getFromContext(OfflinePlayer.class, player) == null)
            throw new VisualNotReadyException("The particle location was not set!");

    }
}
