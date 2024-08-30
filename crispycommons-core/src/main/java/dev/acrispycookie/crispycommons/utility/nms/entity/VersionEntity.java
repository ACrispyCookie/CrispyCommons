package dev.acrispycookie.crispycommons.utility.nms.entity;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface VersionEntity {
    void spawn(Player player);
    void destroy(Player player);
    void updateLocation(Player player);
    void updateMeta(Player player);
    void setGravity(boolean gravity);
    void setDead(boolean dead);
    void setLocation(Location location);
    Location getLocation();
    boolean isDestroyed();
}
