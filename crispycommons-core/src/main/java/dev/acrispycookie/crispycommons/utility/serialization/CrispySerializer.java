package dev.acrispycookie.crispycommons.utility.serialization;

import dev.dejvokep.boostedyaml.serialization.YamlSerializer;
import dev.dejvokep.boostedyaml.utils.supplier.MapSupplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CrispySerializer implements YamlSerializer {

    private static final String serializedTypeKey = "==";
    private static final CrispySerializer instance = new CrispySerializer();
    private final Map<Class<?>, Serializable<?>> adapters = new HashMap<>();
    private final Map<String, Class<?>> aliases = new HashMap<>();

    public <T> void register(@NotNull Class<T> clazz, @NotNull Serializable<T> adapter) {
        this.adapters.put(clazz, adapter);
        this.aliases.put(clazz.getCanonicalName(), clazz);
    }

    @Override
    public @Nullable Object deserialize(@NotNull Map<Object, Object> map) {
        if (!map.containsKey(serializedTypeKey)) {
            return null;
        } else {
            Class<?> type = this.aliases.get(map.get(serializedTypeKey).toString());
            return type == null ? null : this.adapters.get(type).deserialize(map, true);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public @Nullable <T> Map<Object, Object> serialize(@NotNull T object, @NotNull MapSupplier mapSupplier) {
        if (!this.adapters.containsKey(object.getClass())) {
            return null;
        } else {
            Map<Object, Object> serialized = mapSupplier.supply(1);
            serialized.putAll(((Serializable<T>) this.adapters.get(object.getClass())).serialize(object));
            serialized.computeIfAbsent(serializedTypeKey, (k) -> object.getClass().getCanonicalName());
            return serialized;
        }
    }

    @Override
    public @NotNull Set<Class<?>> getSupportedClasses() {
        return this.adapters.keySet();
    }

    @Override
    public @NotNull Set<Class<?>> getSupportedParentClasses() {
        return Collections.emptySet();
    }

    public static CrispySerializer getInstance() {
        return instance;
    }
}
