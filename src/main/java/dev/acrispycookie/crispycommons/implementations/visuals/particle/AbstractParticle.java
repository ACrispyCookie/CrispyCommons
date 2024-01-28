package dev.acrispycookie.crispycommons.implementations.visuals.particle;

import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.types.ParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.particle.CrispyParticle;
import dev.acrispycookie.crispycommons.api.wrappers.particle.Effect;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import org.bukkit.OfflinePlayer;

import java.util.Set;

public abstract class AbstractParticle<T extends Effect> extends AbstractVisual<ParticleData<T>> implements CrispyParticle<T> {


    AbstractParticle(ParticleData<T> data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, data.getElement().getPeriod() != -1 ? timeToLive : 0);
    }

    @Override
    public void prepareShow() {
        data.getElement().start();
    }

    @Override
    public void prepareHide() {
        data.getElement().stop();
    }

    @Override
    public void prepareUpdate() {

    }

    @Override
    public ParticleElement<T> getElement() {
        return data.getElement();
    }

    @Override
    public void setElement(ParticleElement<T> element) {
        data.setElement(element);
    }
}
