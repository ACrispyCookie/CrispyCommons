package dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers;

import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.types.ParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.wrappers.particle.Effect;

public class ParticleData<T extends Effect> implements VisualData {
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

    @Override
    public void assertReady() {
        if (element == null)
            throw new VisualNotReadyException("The particle element was not set!");
    }
}
