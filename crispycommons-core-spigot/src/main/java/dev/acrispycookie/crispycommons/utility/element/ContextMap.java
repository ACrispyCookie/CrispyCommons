package dev.acrispycookie.crispycommons.utility.element;

import dev.acrispycookie.crispycommons.api.element.CrispyElement;
import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A utility class for managing and storing contextual data associated with {@link CrispyElement} instances.
 * <p>
 * The {@code ContextMap} provides a mechanism to store and retrieve context objects by their class types,
 * which can be used by implementations of the {@link CrispyElement} interface to dynamically retrieve
 * their data based on the provided contexts. This class ensures type safety and flexibility when handling
 * multiple contexts in complex operations involving dynamic elements.
 * </p>
 * <p>
 * This class is especially useful when working with {@link DynamicElement} and other similar interfaces,
 * where context-dependent behavior is crucial.
 * </p>
 */
public class ContextMap {

    /**
     * A map that stores context objects associated with their respective class types.
     * <p>
     * The keys in this map are the {@link Class} types of the context objects, and the values are the context
     * objects themselves. This structure allows for flexible and type-safe retrieval of context-specific data
     * within the {@code ContextMap}.
     * </p>
     */
    private final Map<Class<?>, Object> contexts;

    /**
     * Constructs a new {@code ContextMap} with an empty context collection.
     */
    public ContextMap() {
        this.contexts = new HashMap<>();
    }

    /**
     * Adds a context object to the map, associating it with its class type.
     * <p>
     * The context object can later be retrieved by providing its class type.
     * </p>
     *
     * @param clazz the class type of the context object.
     * @param value the context object to be added.
     * @param <C>   the type of the context object.
     * @return this {@link ContextMap} instance for method chaining.
     */
    public <C> @NotNull ContextMap add(@NotNull Class<C> clazz, @NotNull C value) {
        contexts.put(clazz, value);
        return this;
    }

    /**
     * Retrieves an unmodifiable view of the context map.
     * <p>
     * The returned map cannot be modified directly, ensuring the integrity of the stored contexts.
     * </p>
     *
     * @return an unmodifiable {@link Map} containing the context objects and their associated class types.
     */
    public @NotNull Map<Class<?>, Object> get() {
        return Collections.unmodifiableMap(contexts);
    }
}

