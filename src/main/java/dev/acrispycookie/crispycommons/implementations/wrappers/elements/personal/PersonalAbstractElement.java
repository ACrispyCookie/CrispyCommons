package dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal;

import dev.acrispycookie.crispycommons.api.wrappers.elements.PersonalElement;
import org.bukkit.OfflinePlayer;

import java.util.Map;

public abstract class PersonalAbstractElement<T> implements PersonalElement<T> {

    protected Map<OfflinePlayer, T> elements;

    protected PersonalAbstractElement(Map<OfflinePlayer, T> elements) {
        this.elements = elements;
    }

    @Override
    public T getRaw(OfflinePlayer player) {
        return elements.get(player);
    }

    @Override
    public Map<OfflinePlayer, T> getAllPlayers() {
        return elements;
    }

    @Override
    public boolean isDynamic() {
        return false;
    }
}
