package dev.acrispycookie.crispycommons.api.wrappers.elements;

import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.GlobalDynamicElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.PersonalDynamicElement;

public interface CrispyElement<T> extends Cloneable {
    boolean isDynamic();

    static int getMinimumPeriod(PersonalDynamicElement<?>... elements) {
        int period = -1;
        for (PersonalDynamicElement<?> element : elements) {
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
