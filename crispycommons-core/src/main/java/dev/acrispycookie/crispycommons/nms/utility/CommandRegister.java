package dev.acrispycookie.crispycommons.nms.utility;

import dev.acrispycookie.crispycommons.VersionManager;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.java.JavaPlugin;

public interface CommandRegister {

    CommandRegister instance = VersionManager.get().createInstance(CommandRegister.class);

    static CommandRegister newInstance() {
        return instance;
    }

    boolean register(JavaPlugin plugin, String fallbackPrefix, Command command);
    SimpleCommandMap unregister(JavaPlugin plugin, String label);
}
