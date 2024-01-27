package dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.ParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.wrappers.particle.CrispyEffect;

public class ParticleData<T extends CrispyEffect> implements VisualData {
    private ParticleElement<T> element;

    public ParticleData(ParticleElement<T> element) {
        this.element = element;
    }

    public ParticleElement<T> getElement() {
        return element;
    }

    public void setElement(ParticleElement<T> element) {
        this.element = element;
    }
}
