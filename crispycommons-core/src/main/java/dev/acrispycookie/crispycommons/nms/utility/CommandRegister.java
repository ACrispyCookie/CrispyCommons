package dev.acrispycookie.crispycommons.nms.utility;

import dev.acrispycookie.crispycommons.VersionManager;
import dev.acrispycookie.crispycommons.utility.version.MappedVersions;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.java.JavaPlugin;

public interface CommandRegister {

    CommandRegister instance = VersionManager.get().createInstance(CommandRegister.class, new MappedVersions());

    static CommandRegister newInstance() {
        return instance;
    }

    boolean register(JavaPlugin plugin, String fallbackPrefix, Command command);
    SimpleCommandMap unregister(JavaPlugin plugin, String label);
}
