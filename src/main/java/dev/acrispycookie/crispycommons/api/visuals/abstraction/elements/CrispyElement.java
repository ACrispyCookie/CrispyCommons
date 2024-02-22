package dev.acrispycookie.crispycommons.api.visuals.abstraction.elements;

public interface CrispyElement<T> extends Cloneable {

    Object clone() throws CloneNotSupportedException;
    T getRaw();
}
