package dev.acrispycookie.crispycommons.utility.elements;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ContextMap {

    private final Map<Class<?>, Object> contexts;

    public ContextMap() {
        this.contexts = new HashMap<>();
    }

    public <C> ContextMap add(Class<C> clazz, C value) {
        contexts.put(clazz, value);
        return this;
    }

    public Map<Class<?>, Object> get() {
        return Collections.unmodifiableMap(contexts);
    }
}
