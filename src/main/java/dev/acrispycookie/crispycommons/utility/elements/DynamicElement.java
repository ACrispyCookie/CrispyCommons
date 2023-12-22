package dev.acrispycookie.crispycommons.utility.elements;

public abstract class DynamicElement<T> extends AbstractCrispyElement<T> {

    @Override
    public abstract T getElement();

    public DynamicElement(T element) {
        super(element);
    }
}
