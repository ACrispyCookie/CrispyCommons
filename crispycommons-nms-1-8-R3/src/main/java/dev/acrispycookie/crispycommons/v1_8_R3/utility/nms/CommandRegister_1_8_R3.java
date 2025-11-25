package dev.acrispycookie.crispycommons.v1_8_R3.utility.nms;

import dev.acrispycookie.crispycommons.logging.CrispyLogger;
import dev.acrispycookie.crispycommons.platform.SpigotCrispyPlugin;
import dev.acrispycookie.crispycommons.utility.nms.CommandRegister;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Map;

public class CommandRegister_1_8_R3 extends CommandRegister {

    @Override
    public boolean register(@NotNull SpigotCrispyPlugin plugin, @NotNull String fallbackPrefix, @NotNull Command command) {
        return ((CraftServer) plugin.getSpigot().getServer()).getCommandMap().register(fallbackPrefix, command);
    }

    @Override
    public @NotNull CommandMap unregister(@NotNull SpigotCrispyPlugin plugin, @NotNull String label) {
        SimpleCommandMap map = ((CraftServer) plugin.getSpigot().getServer()).getCommandMap();
        Field knownCommandsField = getField(SimpleCommandMap.class, "knownCommands");
        assert knownCommandsField != null : "Known commands field was null. Contact developer.";
        Map<String, Command> knownCommands = getCommandMap(knownCommandsField, map);
        assert knownCommands != null : "Known commands map was null. Contact developer.";
        knownCommands.remove(plugin.getName().toLowerCase() + ":" + label);
        knownCommands.remove(label);
        try {
            knownCommandsField.set(map, knownCommands);
        } catch (IllegalAccessException e) {
            CrispyLogger.printException(plugin, e, "Failed to unregister command " + label + " from plugin " + plugin.getName());
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    private @Nullable Map<String, Command> getCommandMap(@NotNull Field field, @NotNull SimpleCommandMap map) {
        try {
            field.setAccessible(true);
            return (Map<String, Command>) field.get(map);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    private @Nullable Field getField(@NotNull Class<?> clazz, @NotNull String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null) {
                return getField(superClass, fieldName);
            } else {
                return null;
            }
        }
    }
}
