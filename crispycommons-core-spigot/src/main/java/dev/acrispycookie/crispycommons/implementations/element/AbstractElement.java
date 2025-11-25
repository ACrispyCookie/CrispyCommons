package dev.acrispycookie.crispycommons.implementations.element;

import dev.acrispycookie.crispycommons.api.element.CrispyElement;
import dev.acrispycookie.crispycommons.utility.element.ContextMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Base class for elements with a context-based value retrieval.
 * <p>
 * Implements {@link CrispyElement} to manage and retrieve elements based on context.
 * </p>
 *
 * @param <T> the type of the element value.
 * @param <K> the type of the context used for element retrieval.
 */
public abstract class AbstractElement<T, K> implements CrispyElement<T, K> {

    /**
     * A map that associates contexts with their corresponding element values.
     * <p>
     * This map is used to retrieve element values based on the provided context.
     * The key represents the context, and the value is the element associated with that context.
     * </p>
     */
    protected final Map<K, T> elements;

    /**
     * The class type of the context used for retrieving element values.
     * <p>
     * This class type is used to ensure that the context provided during retrieval is of the expected type.
     * It is specified during the construction of the {@code AbstractElement}.
     * </p>
     */
    protected final Class<K> kClass;

    /**
     * Constructs an {@code AbstractElement} with the given elements and context class.
     *
     * @param elements a map of context to element values.
     * @param kClass the class type of the context.
     *
     * @throws NullPointerException if {@code elements} or {@code kClass} is {@code null}.
     */
    protected AbstractElement(@NotNull Map<K, T> elements, @NotNull Class<K> kClass) {
        this.elements = elements;
        this.kClass = kClass;
    }

    /**
     * Retrieves the raw element value for the specified context.
     *
     * @param context the context used to retrieve the element.
     * @return the element value for the given context.
     */
    @Override
    public @NotNull T getRaw(@Nullable K context) {
        return elements.get(context);
    }

    /**
     * Retrieves the data of the element based on the given context map.
     * <p>
     * If none of the contexts given in the {@link ContextMap} matches the context of
     * this element the method tries to retrieve the data using global context and
     * if it still fails, it throws a {@link ContextNotExpectedException} exception.
     * </p>
     *
     * @param contexts the context map used to retrieve the element.
     * @param <C> the type of context in the map.
     * @return the element value for the given context.
     * @throws ContextNotExpectedException if the context type does not match the expected type.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <C> @NotNull T getFromContext(@NotNull ContextMap contexts) {
        for (Map.Entry<Class<?>, Object> context : contexts.get().entrySet()) {
            if (isContext(context.getKey()))
                return ((AbstractElement<T, C>) this).getRaw((C) context.getValue());
        }
        if (isContext(Void.class))
            return getRaw(null);
        throw new ContextNotExpectedException("Couldn't get value of this element because the context is of type " + kClass.getName());
    }

    /**
     * Retrieves the data of the element based on a specific context class and value.
     * <p>
     * If the element isn't of context {@code C} then the method tries to
     * retrieve the data using global context and if it still fails, it throws
     * a {@link ContextNotExpectedException} exception.
     * </p>
     *
     * @param clazz the class type of the context.
     * @param value the context value.
     * @param <C> the type of the context value.
     * @return the element value for the given context.
     * @throws ContextNotExpectedException if the context type does not match the expected type.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <C> @NotNull T getFromContext(@NotNull Class<C> clazz, @NotNull C value) {
        if (isContext(clazz))
            return ((AbstractElement<T, C>) this).getRaw(value);
        if (isContext(Void.class))
            return getRaw(null);
        throw new ContextNotExpectedException("Couldn't get value of this element because the context is of type " + kClass.getName());
    }

    /**
     * Returns the class type of the context.
     *
     * @return the class type of the context.
     */
    @Override
    public @NotNull Class<K> getContextClass() {
        return kClass;
    }

    /**
     * Checks if the given class is compatible with the context class.
     *
     * @param clazz the class to check.
     * @return {@code true} if the class is assignable from the context class, otherwise {@code false}.
     */
    @Override
    public boolean isContext(@NotNull Class<?> clazz) {
        return kClass.isAssignableFrom(clazz);
    }

    /**
     * Indicates if the element is dynamic.
     *
     * @return {@code false} as this implementation is not dynamic.
     */
    @Override
    public boolean isDynamic() {
        return false;
    }
}

