package dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers;

import org.bukkit.entity.Player;

public class NameTagContext {

    private final Player player;
    private final Player receiver;

    public NameTagContext(Player player, Player receiver) {
        this.player = player;
        this.receiver = receiver;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getReceiver() {
        return receiver;
    }
}
