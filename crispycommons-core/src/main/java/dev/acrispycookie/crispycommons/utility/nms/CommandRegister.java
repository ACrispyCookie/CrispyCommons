package dev.acrispycookie.crispycommons.utility.nms;

import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.Versioned;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class CommandRegister implements Versioned {

    private static final CommandRegister instance = VersionManager.createInstance(CommandRegister.class, new MappedVersions());

    public static CommandRegister newInstance() {
        return instance;
    }

    public abstract boolean register(JavaPlugin plugin, String fallbackPrefix, Command command);
    public abstract SimpleCommandMap unregister(JavaPlugin plugin, String label);

    public static MappedVersions getRemapped() {
        return new MappedVersions();
    }
}
