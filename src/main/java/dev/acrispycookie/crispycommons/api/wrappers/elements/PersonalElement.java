package dev.acrispycookie.crispycommons.api.wrappers.elements;

import org.bukkit.OfflinePlayer;

public interface PersonalElement<T> extends CrispyElement<T> {
    T getRaw(OfflinePlayer player);
}
