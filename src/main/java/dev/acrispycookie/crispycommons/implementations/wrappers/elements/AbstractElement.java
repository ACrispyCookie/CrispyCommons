package dev.acrispycookie.crispycommons.implementations.wrappers.elements;

import dev.acrispycookie.crispycommons.api.wrappers.elements.PrivateCrispyElement;
import org.bukkit.OfflinePlayer;

import java.util.Map;

public abstract class AbstractElement<T> implements PrivateCrispyElement<T> {

    protected Map<OfflinePlayer, T> elements;

    protected AbstractElement(Map<OfflinePlayer, T> elements) {
        this.elements = elements;
    }

    @Override
    public T getRaw(OfflinePlayer player) {
        return elements.get(player);
    }

    @Override
    public boolean isDynamic() {
        return false;
    }
}
