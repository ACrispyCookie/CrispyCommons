package dev.acrispycookie.crispycommons.platform.player;

import dev.acrispycookie.crispycommons.platform.player.PlatformPlayer;
import org.bukkit.entity.Player;

public class SpigotPlayer implements PlatformPlayer {

    private final Player player;

    public SpigotPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean hasPermission(String permission) {
        return player.hasPermission(permission);
    }
}
