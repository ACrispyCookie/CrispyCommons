package dev.acrispycookie.crispycommons.api.wrappers.elements;

import org.bukkit.OfflinePlayer;

public interface PrivateCrispyElement<T> extends CrispyElement<T> {
    T getRaw(OfflinePlayer player);
}
