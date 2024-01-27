package dev.acrispycookie.crispycommons.api.visuals.abstraction.elements;

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
