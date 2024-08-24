package dev.acrispycookie.crispycommons.nms.v1_8_R3.entity.custom;

import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.nms.entity.custom.CustomItem;
import dev.acrispycookie.crispycommons.nms.entity.spigot.VersionArmorStand;
import dev.acrispycookie.crispycommons.nms.entity.spigot.VersionItem;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CustomItem_1_8_R3 implements CustomItem {

    private final VersionArmorStand armorStand;
    private final VersionItem item;

    public CustomItem_1_8_R3(Location location, CrispyItemStack initialDisplay) {
        armorStand = VersionArmorStand.newInstance(location);
        armorStand.setInvisible(true);
        armorStand.setNoClip(true); // Disables interaction
        armorStand.setBasePlate(true);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(false);
        armorStand.setSmall(true);

        item = VersionItem.newInstance(location, initialDisplay);
        item.setDespawnDelay(Integer.MAX_VALUE); // Makes the item entity persistent (doesn't despawn)
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
        armorStand.spawn(player);
        armorStand.updateMeta(player);

        item.spawn(player);
        item.updateMeta(player);
        armorStand.attachEntity(player, item);
    }

    @Override
    public void destroy(@NotNull Player player) {
        armorStand.destroy(player);
        item.destroy(player);
    }

    @Override
    public void update(@NotNull Location location, @NotNull Player player) {
        item.setLocation(location);
        item.updateLocation(player);
        item.updateMeta(player);
    }

    @Override
    public void setDisplay(CrispyItemStack itemStack) {
        item.setItemStack(itemStack);
    }
}
