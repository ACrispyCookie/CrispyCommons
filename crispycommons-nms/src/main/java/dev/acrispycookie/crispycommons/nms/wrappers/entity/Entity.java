package dev.acrispycookie.crispycommons.nms.wrappers.entity;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Entity {
    void spawn(Player player);
    void destroy(Player player);
    void updateLocation(Player player);
    void updateMeta(Player player);
    void setDead(boolean dead);
    void setLocation(Location location);
    Location getLocation();
}
