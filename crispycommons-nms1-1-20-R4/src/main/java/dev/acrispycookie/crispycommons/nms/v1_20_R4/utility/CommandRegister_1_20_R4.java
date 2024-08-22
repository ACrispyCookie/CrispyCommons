package dev.acrispycookie.crispycommons.nms.v1_20_R4.utility;

import dev.acrispycookie.crispycommons.nms.wrappers.utilities.CommandRegister;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.v1_20_R4.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Map;

public class CommandRegister_1_20_R4 implements CommandRegister {

    @Override
    public boolean register(JavaPlugin plugin, String fallbackPrefix, Command command) {
        return ((CraftServer) plugin.getServer()).getCommandMap().register(fallbackPrefix, command);
    }

    @Override
    public SimpleCommandMap unregister(JavaPlugin plugin, String label) {
        SimpleCommandMap map = ((CraftServer) plugin.getServer()).getCommandMap();
        Field knownCommandsField = getField(SimpleCommandMap.class, "knownCommands");
        Map<String, Command> knownCommands = getCommandMap(knownCommandsField, map);
        knownCommands.remove(plugin.getName().toLowerCase() + ":" + label);
        knownCommands.remove(label);
        try {
            knownCommandsField.set(map, knownCommands);
        } catch (IllegalAccessException e) {
            System.out.println("Failed to unregister command " + label + " from plugin " + plugin.getName());
        }
        return map;
    }
    
    private Map<String, Command> getCommandMap(Field field, SimpleCommandMap map) {
        try {
            field.setAccessible(true);
            return (Map<String, Command>) field.get(map);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    private Field getField(Class<?> clazz, String fieldName) {
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
