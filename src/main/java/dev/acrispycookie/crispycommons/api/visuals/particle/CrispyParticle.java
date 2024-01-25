package dev.acrispycookie.crispycommons.api.visuals.particle;

import dev.acrispycookie.crispycommons.api.wrappers.particle.CrispyEffect;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.ParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyAccessibleVisual;

public interface CrispyParticle<T extends CrispyEffect> extends CrispyAccessibleVisual<ParticleElement<T>> {

    long getDuration();
    void setDuration(long duration);
    long getPeriod();
    void setPeriod(long period);
}
