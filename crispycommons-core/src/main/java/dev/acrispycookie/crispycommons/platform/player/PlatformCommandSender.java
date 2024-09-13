package dev.acrispycookie.crispycommons.platform.player;

import net.kyori.adventure.text.Component;

public interface PlatformCommandSender {
    boolean hasPermission(String permission);
    void sendMessage(Component component);
}
