package dev.acrispycookie.crispycommons.api.wrappers.elements;

public interface DynamicElement<T> extends CrispyElement<T> {

    void start();
    void stop();
    void setUpdate(Runnable runnable);
}
