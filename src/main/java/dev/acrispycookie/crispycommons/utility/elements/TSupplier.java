package dev.acrispycookie.crispycommons.utility.elements;

import org.bukkit.OfflinePlayer;

import java.util.function.Function;

public class TSupplier<T> {

    private final Function<OfflinePlayer, ? extends T> function;

    public TSupplier(Function<OfflinePlayer, ? extends T> function) {
        this.function = function;
    }

    public Function<OfflinePlayer, ? extends T> getFunction() {
        return function;
    }
}
