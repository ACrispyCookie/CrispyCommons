package dev.acrispycookie.crispycommons.implementations.visuals.particle;

import dev.acrispycookie.crispycommons.api.visuals.particle.CrispyParticle;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.ParticleElement;
import dev.acrispycookie.crispycommons.api.wrappers.particle.Effect;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalGeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalGeneralElement;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public abstract class AbstractParticle<T extends Effect> extends AbstractVisual<ParticleData<T>> implements CrispyParticle<T> {


    AbstractParticle(ParticleData<T> data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long> timeToLive, UpdateMode updateMode) {
        super(data, receivers, timeToLive, updateMode);
    }

    @Override
    protected void prepareShow() {
        data.getElement().start();
        data.getLocation().start();
    }

    @Override
    protected void prepareHide() {
        data.getElement().stop();
        data.getLocation().stop();
    }

    @Override
    public ParticleElement<T> getElement() {
        return data.getElement();
    }

    @Override
    public void setElement(ParticleElement<T> element) {
        data.setElement(element);
    }

    @Override
    public GeneralElement<Location> getLocation() {
        return data.getLocation();
    }

    @Override
    public void setLocation(GeneralElement<Location> location) {
        data.setLocation(location);
    }

    protected Location getLocation(Player player) {
        return data.getLocation() instanceof GlobalGeneralElement ?
                ((GlobalGeneralElement<Location>) data.getLocation()).getRaw() :
                ((PersonalGeneralElement<Location>) data.getLocation()).getRaw(player);
    }
}
