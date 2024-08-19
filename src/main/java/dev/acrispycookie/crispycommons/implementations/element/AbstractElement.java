package dev.acrispycookie.crispycommons.implementations.element;

import dev.acrispycookie.crispycommons.api.element.CrispyElement;
import dev.acrispycookie.crispycommons.utility.element.ContextMap;

import java.util.Map;

public abstract class AbstractElement<T, K> implements CrispyElement<T, K> {

    protected final Map<K, T> elements;
    protected final Class<K> kClass;

    protected AbstractElement(Map<K, T> elements, Class<K> kClass) {
        this.elements = elements;
        this.kClass = kClass;
    }

    @Override
    public T getRaw(K context) {
        return elements.get(context);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <C> T getFromContext(ContextMap contexts) {
        for (Map.Entry<Class<?>, Object> context : contexts.get().entrySet()) {
            if (isContext(context.getKey()))
                return ((AbstractElement<T, C>) this).getRaw((C) context.getValue());
        }
        if (isContext(Void.class))
            return getRaw(null);
        throw new ContextNotExpectedException("Couldn't get value of this element because the context is of type " + kClass.getName());
    }

    @Override
    @SuppressWarnings("unchecked")
    /*
    Checks if the element is of context C and returns the value of the element if it is,
    or checks if it doesn't have any context.
     */
    public <C> T getFromContext(Class<C> clazz, C value) {
        if (isContext(clazz))
            return ((AbstractElement<T, C>) this).getRaw(value);
        if (isContext(Void.class))
            return getRaw(null);
        throw new ContextNotExpectedException("Couldn't get value of this element because the context is of type " + kClass.getName());
    }

    @Override
    public Class<K> getContextClass() {
        return kClass;
    }

    @Override
    public boolean isContext(Class<?> clazz) {
        return kClass.isAssignableFrom(clazz);
    }

    @Override
    public boolean isDynamic() {
        return false;
    }
}
