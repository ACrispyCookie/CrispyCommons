package dev.acrispycookie.crispycommons.implementations.gui.abstraction;

import dev.acrispycookie.crispycommons.api.gui.abstraction.GuiData;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTrackedGui<T extends GuiData> extends AbstractGui<T> {

    protected final Map<OfflinePlayer, Boolean> viewers = new HashMap<>();

    public AbstractTrackedGui(T data) {
        super(data);
    }

    public boolean isPlayerViewing(@NotNull OfflinePlayer player) {
        return viewers.containsKey(player) && viewers.get(player);
    }
}
