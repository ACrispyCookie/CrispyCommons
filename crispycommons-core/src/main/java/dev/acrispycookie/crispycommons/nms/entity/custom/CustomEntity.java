package dev.acrispycookie.crispycommons.nms.entity.custom;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface CustomEntity {

    double offsetPerLine();
    Location getLocation();
    void spawn(@NotNull Location location, @NotNull Player player);
    void destroy(@NotNull Player player);
    void update(@NotNull Location location, @NotNull Player player);
}
