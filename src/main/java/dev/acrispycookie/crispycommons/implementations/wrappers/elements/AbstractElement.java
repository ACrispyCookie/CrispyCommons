package dev.acrispycookie.crispycommons.implementations.wrappers.elements;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.api.wrappers.elements.CrispyElement;
import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.ItemElement;

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
    public <C> T getFromContext(Map<Class<C>, C> contexts) {
        for (Class<C> key : contexts.keySet()) {
            if (isContext(key))
                return ((AbstractElement<T, C>) this).getRaw(contexts.get(key));
        }
        if (isContext(Void.class))
            return getRaw(null);
        throw new CrispyVisual.ContextNotExpectedException("Couldn't get value of this element because the context is of type " + kClass.getName());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <C> T getFromContext(Class<C> clazz, C value) {
        if (isContext(clazz))
            return ((AbstractElement<T, C>) this).getRaw(value);
        if (isContext(Void.class))
            return getRaw(null);
        throw new CrispyVisual.ContextNotExpectedException("Couldn't get value of this element because the context is of type " + kClass.getName());
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
