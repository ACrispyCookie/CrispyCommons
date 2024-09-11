package dev.acrispycookie.crispycommons.version.utility;

import org.jetbrains.annotations.NotNull;

public class ArgPair<T> {

    private final Class<T> clazz;
    private final T arg;

    public ArgPair(@NotNull Class<T> clazz, @NotNull T arg) {
        this.clazz = clazz;
        this.arg = arg;
    }

    public @NotNull Class<T> getClazz() {
        return clazz;
    }

    public @NotNull T getArg() {
        return arg;
    }
}
