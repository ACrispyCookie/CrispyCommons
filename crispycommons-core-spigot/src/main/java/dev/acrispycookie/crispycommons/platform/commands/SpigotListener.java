package dev.acrispycookie.crispycommons.platform.commands;

import org.bukkit.event.Listener;

public interface SpigotListener extends PlatformListener {

    Listener getSpigot();
}
