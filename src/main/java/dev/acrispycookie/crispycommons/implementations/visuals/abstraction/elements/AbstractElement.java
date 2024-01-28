package dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.CrispyElement;

public class AbstractElement<T> implements CrispyElement<T> {

    protected T element;

    protected AbstractElement(T element) {
        this.element = element;
    }

    @Override
    public T getRaw() {
        return element;
    }
}
