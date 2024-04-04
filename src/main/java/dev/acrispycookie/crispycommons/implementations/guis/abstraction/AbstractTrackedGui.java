package dev.acrispycookie.crispycommons.implementations.guis.abstraction;

import dev.acrispycookie.crispycommons.api.guis.abstraction.GuiData;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTrackedGui<T extends GuiData> extends AbstractGui<T> {

    protected Map<OfflinePlayer, Boolean> viewers = new HashMap<>();

    public AbstractTrackedGui(T data) {
        super(data);
    }

    public boolean isPlayerViewing(OfflinePlayer player) {
        return viewers.containsKey(player) && viewers.get(player);
    }
}
