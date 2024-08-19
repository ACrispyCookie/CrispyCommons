package dev.acrispycookie.crispycommons.utility.element;

import java.util.function.Function;

/**
 * A simple wrapper class for a {@link Function}, allowing type flexibility when working with
 * functions that have different type parameters.
 *
 * @param <K> the type of the input to the function.
 * @param <T> the type of the result of the function.
 */
public class MyElementSupplier<K, T> {

    private final Function<K, ? extends T> function;

    /**
     * Constructs a {@code MyElementSupplier} with the specified function.
     *
     * @param function the function to be wrapped by this supplier.
     */
    public MyElementSupplier(Function<K, ? extends T> function) {
        this.function = function;
    }

    /**
     * Returns the wrapped function.
     *
     * @return the function wrapped by this {@code MyElementSupplier}.
     */
    public Function<K, ? extends T> getFunction() {
        return function;
    }
}

