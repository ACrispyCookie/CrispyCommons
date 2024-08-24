package dev.acrispycookie.crispycommons.utility.version;

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
