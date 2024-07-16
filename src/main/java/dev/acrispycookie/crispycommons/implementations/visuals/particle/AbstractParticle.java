package dev.acrispycookie.crispycommons.implementations.visuals.particle;

import dev.acrispycookie.crispycommons.api.visuals.particle.CrispyParticle;
import dev.acrispycookie.crispycommons.api.wrappers.particle.Effect;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.ParticleElement;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

import java.util.Set;

public abstract class AbstractParticle<T extends Effect> extends AbstractVisual<ParticleData<T>> implements CrispyParticle<T> {


    AbstractParticle(ParticleData<T> data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long, ?> timeToLive, UpdateMode updateMode) {
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
    public ParticleElement<T, ?> getElement() {
        return data.getElement();
    }

    @Override
    public void setElement(ParticleElement<T, ?> element) {
        data.getElement().stop();
        data.setElement(element);
        data.getElement().setUpdate(this::update);
        if (isAnyoneWatching()) {
            data.getElement().start();
            update();
        }
    }

    @Override
    public GeneralElement<Location, ?> getLocation() {
        return data.getLocation();
    }

    @Override
    public void setLocation(GeneralElement<Location, ?> location) {
        data.getLocation().stop();
        data.setLocation(location);
        data.getLocation().setUpdate(this::update);
        if (isAnyoneWatching()) {
            data.getLocation().start();
            update();
        }
    }
}
