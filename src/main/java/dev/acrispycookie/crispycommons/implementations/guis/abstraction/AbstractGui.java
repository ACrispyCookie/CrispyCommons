package dev.acrispycookie.crispycommons.implementations.guis.abstraction;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.guis.abstraction.CrispyGui;
import dev.acrispycookie.crispycommons.api.guis.abstraction.GuiData;
import dev.acrispycookie.crispycommons.utility.logging.CrispyLogger;
import org.bukkit.entity.Player;

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
    public void open(Player p) {
        try {
            data.assertReady();
            openInternal(p);
        } catch (GuiData.GuiNotReadyException e) {
            CrispyLogger.printException(CrispyCommons.getPlugin(), e, "This GUI is not ready to be opened!");
        }
    }

    @Override
    public void close(Player p) {
        closeInternal(p);
    }
}
