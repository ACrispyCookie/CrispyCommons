package dev.acrispycookie.crispycommons.implementations.wrappers.elements.context;

import org.bukkit.OfflinePlayer;

public class NameTagContext {

    private final OfflinePlayer player;
    private final OfflinePlayer receiver;

    public NameTagContext(OfflinePlayer player, OfflinePlayer receiver) {
        this.player = player;
        this.receiver = receiver;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    public OfflinePlayer getReceiver() {
        return receiver;
    }
}
