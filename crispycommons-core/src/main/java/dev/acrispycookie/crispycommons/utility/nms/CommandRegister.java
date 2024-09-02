package dev.acrispycookie.crispycommons.utility.nms;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.Versioned;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public abstract class CommandRegister implements Versioned {

    private static final CommandRegister instance = VersionManager.createInstance(CommandRegister.class, new MappedVersions());

    public static @NotNull CommandRegister newInstance() {
        return instance;
    }

    public abstract boolean register(@NotNull JavaPlugin plugin, @NotNull String fallbackPrefix, @NotNull Command command);
    public abstract @NotNull SimpleCommandMap unregister(@NotNull JavaPlugin plugin, @NotNull String label);

    public static @NotNull MappedVersions getRemapped() {
        return new MappedVersions();
    }
}
