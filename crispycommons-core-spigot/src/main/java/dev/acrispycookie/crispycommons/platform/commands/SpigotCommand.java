package dev.acrispycookie.crispycommons.platform.commands;

import dev.acrispycookie.crispycommons.platform.player.PlatformCommandSender;
import dev.acrispycookie.crispycommons.platform.player.SpigotCommandSender;
import org.bukkit.command.Command;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface SpigotCommand extends PlatformCommand {

    Command getSpigot();

    @Override
    default boolean execute(@NotNull PlatformCommandSender sender, @NotNull String usage, String[] args) {
        return getSpigot().execute(((SpigotCommandSender) sender).getSpigot(), usage, args);
    }

    @Override
    default @NotNull List<String> tabComplete(@NotNull PlatformCommandSender sender, @NotNull String alias, String[] args) {
        return getSpigot().tabComplete(((SpigotCommandSender) sender).getSpigot(), alias, args);
    }
}
