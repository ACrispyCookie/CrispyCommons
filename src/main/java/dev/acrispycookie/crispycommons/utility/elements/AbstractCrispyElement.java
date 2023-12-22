package dev.acrispycookie.crispycommons.utility.elements;

public class AbstractCrispyElement<T> implements CrispyElement<T> {

    protected T element;

    public AbstractCrispyElement(T element) {
        this.element = element;
    }

    @Override
    public T getElement() {
        return element;
    }
}
