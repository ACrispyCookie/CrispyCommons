package dev.acrispycookie.crispycommons.nms.wrappers.utilities;

import dev.acrispycookie.crispycommons.VersionManager;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.java.JavaPlugin;

public interface CommandRegister {

    static CommandRegister newInstance() {
        return VersionManager.get().createInstance(CommandRegister.class);
    }

    boolean register(JavaPlugin plugin, String fallbackPrefix, Command command);
    SimpleCommandMap unregister(JavaPlugin plugin, String label);
}
