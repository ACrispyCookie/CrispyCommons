package dev.acrispycookie.crispycommons.implementations.guis.abstraction;

import dev.acrispycookie.crispycommons.api.guis.abstraction.GuiData;
import org.bukkit.entity.Player;

public abstract class AbstractUntrackedGui<T extends GuiData> extends AbstractGui<T> {

    public AbstractUntrackedGui(T data) {
        super(data);
    }

    @Override
    public void close(Player p) {

    }

    @Override
    public void closeInternal(Player p) {

    }
}
