package dev.acrispycookie.crispycommons.platform.player;

import dev.acrispycookie.crispycommons.platform.player.PlatformCommandSender;
import org.bukkit.command.CommandSender;

public class SpigotCommandSender implements PlatformCommandSender {

    private final CommandSender sender;

    public SpigotCommandSender(CommandSender sender) {
        this.sender = sender;
    }

    public CommandSender getSender() {
        return sender;
    }

    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }
}
