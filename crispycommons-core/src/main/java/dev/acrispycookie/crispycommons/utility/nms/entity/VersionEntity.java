package dev.acrispycookie.crispycommons.utility.nms.entity;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface VersionEntity {
    void spawn(@NotNull Player player);
    void destroy(@NotNull Player player);
    void updateLocation(@NotNull Player player);
    void updateMeta(@NotNull Player player);
    void setGravity(boolean gravity);
    void setDead(boolean dead);
    void setLocation(@NotNull Location location);
    @NotNull Location getLocation();
    boolean isDestroyed();
}
