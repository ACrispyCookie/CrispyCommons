package dev.acrispycookie.crispycommons.utility.elements;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class MapHelper {

    @SafeVarargs
    public static <K, V> Map<K, V> mapOf(Map.Entry<K, V>... entries) {
        Map<K, V> map = new HashMap<>();
        for (Map.Entry<K, V> entry : entries) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    // Helper method to create Map.Entry instances concisely
    public static <K, V> Map.Entry<K, V> entry(K key, V value) {
        return new AbstractMap.SimpleEntry<>(key, value);
    }
}
