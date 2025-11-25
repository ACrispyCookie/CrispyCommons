package dev.acrispycookie.crispycommons.platform.player;

import dev.acrispycookie.crispycommons.SpigotCrispyCommons;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

public interface SpigotCommandSender extends PlatformCommandSender {

    CommandSender getSpigot();

    @Override
    default boolean hasPermission(String permission) {
        return getSpigot().hasPermission(permission);
    }

    @Override
    default void sendMessage(Component text) {
        Audience audience = SpigotCrispyCommons.getInstance().getBukkitAudiences().sender(getSpigot());
        audience.sendMessage(text);
    }
}
