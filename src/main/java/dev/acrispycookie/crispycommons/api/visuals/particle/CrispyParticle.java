package dev.acrispycookie.crispycommons.api.visuals.particle;

import dev.acrispycookie.crispycommons.api.wrappers.particle.CrispyEffect;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.ParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;

public interface CrispyParticle<T extends CrispyEffect> extends CrispyAccessibleVisual<ParticleData<T>> {

    long getDuration();
    void setDuration(long duration);
    long getPeriod();
    void setPeriod(long period);
}
