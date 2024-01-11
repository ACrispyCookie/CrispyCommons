package dev.acrispycookie.crispycommons.implementations.visuals.particles;

import dev.acrispycookie.crispycommons.implementations.wrappers.particle.CrispyEffect;
import dev.acrispycookie.crispycommons.utility.visual.CrispyAccessibleVisual;

public interface CrispyParticle<T extends CrispyEffect> extends CrispyAccessibleVisual<CrispyEffect> {

    long getDuration();
    void setDuration(long duration);
    long getPeriod();
    void setPeriod(long period);
}
