package dev.acrispycookie.crispycommons.api.wrappers.elements;

import org.bukkit.OfflinePlayer;

import java.util.Map;

public interface PersonalElement<T> extends CrispyElement<T> {
    T getRaw(OfflinePlayer player);
    Map<OfflinePlayer, T> getAllPlayers();
}
