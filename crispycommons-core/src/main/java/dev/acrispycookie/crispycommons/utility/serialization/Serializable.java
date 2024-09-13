package dev.acrispycookie.crispycommons.utility.serialization;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface Serializable<T> {

    T deserialize(@NotNull Map<Object, Object> map, boolean doesntMatter);
    Map<Object, Object> serialize(@NotNull T t);
}
