package dev.acrispycookie.crispycommons.utility.element;

import java.util.function.Function;

public class MyElementSupplier<K, T> {

    private final Function<K, ? extends T> function;

    public MyElementSupplier(Function<K, ? extends T> function) {
        this.function = function;
    }

    public Function<K, ? extends T> getFunction() {
        return function;
    }
}
