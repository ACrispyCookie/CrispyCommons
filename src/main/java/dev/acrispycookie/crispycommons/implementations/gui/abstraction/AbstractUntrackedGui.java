package dev.acrispycookie.crispycommons.implementations.gui.abstraction;

import dev.acrispycookie.crispycommons.api.gui.abstraction.GuiData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractUntrackedGui<T extends GuiData> extends AbstractGui<T> {

    public AbstractUntrackedGui(T data) {
        super(data);
    }

    @Override
    public void close(@NotNull Player player) {

    }

    @Override
    public void closeInternal(Player p) {

    }
}
