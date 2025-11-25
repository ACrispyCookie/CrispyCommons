package dev.acrispycookie.crispycommons.platform.commands;

import dev.acrispycookie.crispycommons.platform.player.PlatformCommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface PlatformCommand {

    boolean execute(@NotNull PlatformCommandSender sender, @NotNull String usage, String[] args);
    @NotNull List<String> tabComplete(@NotNull PlatformCommandSender sender, @NotNull String alias, String[] args);
}
