package dev.acrispycookie.crispycommons.api.element;

import dev.acrispycookie.crispycommons.utility.element.ContextMap;

public interface CrispyElement<T, K> extends Cloneable {
    T getRaw(K context);
    <C> T getFromContext(ContextMap contexts);
    <C> T getFromContext(Class<C> clazz, C value);
    boolean isDynamic();
    Class<K> getContextClass();
    boolean isContext(Class<?> clazz);

    static int getMinimumPeriod(DynamicElement<?, ?>... elements) {
        int period = -1;
        for (DynamicElement<?, ?> element : elements) {
            if (element == null || !element.isDynamic())
                continue;
            if (period == -1) {
                period = element.getPeriod();
                continue;
            }
            period = Math.min(period, element.getPeriod());
        }
        return period;
    }
}
