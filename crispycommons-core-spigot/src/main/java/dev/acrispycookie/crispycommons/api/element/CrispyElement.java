package dev.acrispycookie.crispycommons.api.element;

import dev.acrispycookie.crispycommons.utility.element.ContextMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a generic element that can be context-aware and dynamic.
 * <p>
 * The {@code CrispyElement} interface defines methods for handling elements that can be accessed or modified based on
 * a context of type {@code K}. Implementations of this interface must provide mechanisms to retrieve raw data, check
 * if the element is dynamic, and determine its context class. The interface also supports context-based retrieval
 * and provides utility methods for working with dynamic elements.
 * </p>
 *
 * @param <T> the type of the data or result this element provides.
 * @param <K> the type of the context used to retrieve or interact with the element.
 */
public interface CrispyElement<T, K> {

    /**
     * Retrieves the raw data of the element based on the provided context.
     *
     * @param context the context of type {@code K} used to access the raw data.
     * @return the raw data of type {@code T} based on the given context.
     */
    @NotNull T getRaw(@Nullable K context);

    /**
     * Retrieves the data of the element based on the given context map.
     * <p>
     * If none of the contexts given in the {@link ContextMap} matches the context of
     * this element the method tries to retrieve the data using global context and
     * if it still fails, it throws a {@link ContextNotExpectedException} exception.
     * </p>
     *
     * @param contexts the {@link ContextMap} containing context data used to access the element's data.
     * @param <C> the type of the context contained in the {@link ContextMap}.
     * @return the data of type {@code T} based on the context map.
     */
    <C> @NotNull T getFromContext(@NotNull ContextMap contexts);

    /**
     * Retrieves the data of the element based on a specific context class and value.
     * <p>
     * If the element isn't of context {@code C} then the method tries to
     * retrieve the data using global context and if it still fails, it throws
     * a {@link ContextNotExpectedException} exception.
     * </p>
     *
     * @param clazz the {@link Class} of the context type used to access the element's data.
     * @param value the context value used to retrieve the element's data.
     * @param <C> the type of the context value.
     * @return the data of type {@code T} based on the context class and value.
     */
    <C> @NotNull T getFromContext(@NotNull Class<C> clazz, @NotNull C value);

    /**
     * Checks if the element is dynamic.
     *
     * @return {@code true} if the element is dynamic; {@code false} otherwise.
     */
    boolean isDynamic();

    /**
     * Retrieves the {@link Class} of the context type used by this element.
     *
     * @return the {@link Class} object representing the context type {@code K}.
     */
    @NotNull Class<K> getContextClass();

    /**
     * Checks if the element's context is of a specific class type.
     *
     * @param clazz the {@link Class} object representing the context type to check.
     * @return {@code true} if the element's context is of the specified class type; {@code false} otherwise.
     */
    boolean isContext(@NotNull Class<?> clazz);

    /**
     * Calculates the minimum period among the provided dynamic elements.
     * <p>
     * This static utility method finds the smallest period among an array of {@link DynamicElement} instances.
     * </p>
     *
     * @param elements the array of {@link DynamicElement} instances to check.
     * @return the minimum period of the provided dynamic elements, or {@code -1} if none are dynamic.
     */
    static int getMinimumPeriod(@NotNull DynamicElement<?, ?>... elements) {
        int period = -1;
        for (DynamicElement<?, ?> element : elements) {
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

    /**
     * Thrown to indicate that a context type was not expected in an element.
     * <p>
     * This exception is used to signal that the wrong type of context was given for the retrieval of the value of an element.
     * It extends {@link RuntimeException} and provides a constructor to specify a detailed error message.
     * </p>
     */
    class ContextNotExpectedException extends RuntimeException {

        /**
         * Constructs a new {@code ContextNotExpectedException} with the specified detail message.
         *
         * @param message the detail message to be reported when the exception is thrown.
         */
        public ContextNotExpectedException(String message) {
            super(message);
        }
    }
}
