package dev.acrispycookie.crispycommons.version.utility;

public class ArgPair<T> {

    Class<T> clazz;
    T arg;

    public ArgPair(Class<T> clazz, T arg) {
        this.clazz = clazz;
        this.arg = arg;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public T getArg() {
        return arg;
    }
}
