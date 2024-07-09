package dev.acrispycookie.crispycommons.api.wrappers.elements;

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
}
