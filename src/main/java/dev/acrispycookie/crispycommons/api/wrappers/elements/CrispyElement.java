package dev.acrispycookie.crispycommons.api.wrappers.elements;

import dev.acrispycookie.crispycommons.implementations.wrappers.elements.DynamicElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.GlobalDynamicElement;

public interface CrispyElement<T> extends Cloneable {
    boolean isDynamic();

    static int getMinimumPeriod(DynamicElement<?>... elements) {
        int period = -1;
        for (DynamicElement<?> element : elements) {
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

    static int getMinimumPeriod(GlobalDynamicElement<?>... elements) {
        int period = -1;
        for (GlobalDynamicElement<?> element : elements) {
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
