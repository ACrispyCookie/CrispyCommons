package dev.acrispycookie.crispycommons.implementations.gui.abstraction;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.gui.abstraction.CrispyGui;
import dev.acrispycookie.crispycommons.api.gui.abstraction.GuiData;
import dev.acrispycookie.crispycommons.utility.logging.CrispyLogger;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractGui<T extends GuiData> implements CrispyGui {

    protected final T data;
    protected abstract void openInternal(Player p);
    protected abstract void closeInternal(Player p);

    public AbstractGui(T data) {
        this.data = data;
    }

    protected T getData() {
        return data;
    }

    @Override
    public void open(@NotNull Player player) {
        try {
            data.assertReady();
            openInternal(player);
        } catch (GuiData.GuiNotReadyException e) {
            CrispyLogger.printException(CrispyCommons.getPlugin(), e, "This GUI is not ready to be opened!");
        }
    }

    @Override
    public void close(@NotNull Player player) {
        closeInternal(player);
    }
}
