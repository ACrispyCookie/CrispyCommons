package dev.acrispycookie.crispycommons.platform.player;

import org.bukkit.entity.Player;

public interface SpigotPlayer extends PlatformPlayer, SpigotCommandSender {

    Player getSpigot();
}
