package dev.acrispycookie.crispycommons.nms.v1_20_R3.entity.custom;

import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.nms.entity.custom.CustomItem;
import dev.acrispycookie.crispycommons.nms.entity.spigot.VersionArmorStand;
import dev.acrispycookie.crispycommons.nms.entity.spigot.VersionItem;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CustomItem_1_20_R3 implements CustomItem {

    private final VersionItem item;

    public CustomItem_1_20_R3(Location location, CrispyItemStack itemStack) {
        item = VersionItem.newInstance(location, itemStack);
        item.setDespawnDelay(Integer.MAX_VALUE); // Makes the item entity persistent (doesn't despawn)
        item.setGravity(false);
    }

    @Override
    public double offsetPerLine() {
        return 0.5;
    }

    @Override
    public Location getLocation() {
        return item.getLocation();
    }

    @Override
    public void spawn(@NotNull Location location, @NotNull Player player) {
        item.spawn(player);
    }

    @Override
    public void destroy(@NotNull Player player) {
        item.destroy(player);
    }

    @Override
    public void update(@NotNull Location location, @NotNull Player player) {
        item.setLocation(location);
        item.updateMeta(player);
        item.updateLocation(player);
    }

    @Override
    public void setDisplay(CrispyItemStack itemStack) {
        item.setItemStack(itemStack);
    }
}
