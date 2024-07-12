package dev.acrispycookie.crispycommons.api.wrappers.elements;

public interface DynamicElement<T, K> extends CrispyElement<T, K> {
    void start();
    void stop();
    int getPeriod();
    void setUpdate(Runnable runnable);
}
