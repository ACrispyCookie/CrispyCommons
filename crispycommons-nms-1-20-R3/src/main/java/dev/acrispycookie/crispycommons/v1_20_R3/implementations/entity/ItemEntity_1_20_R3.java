package dev.acrispycookie.crispycommons.v1_20_R3.implementations.entity;

import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import dev.acrispycookie.crispycommons.implementations.entity.ItemEntity;
import dev.acrispycookie.crispycommons.utility.nms.entity.VersionItem;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ItemEntity_1_20_R3 extends ItemEntity {

    /**
     * The version independent instance of a custom item.
     * <p>
     * This instance manages the spawning, updating and removing of the custom item with
     * the different implementations based on different versions of Minecraft.
     * </p>
     */
    private final VersionItem item;

    public ItemEntity_1_20_R3(@NotNull ItemElement<?> element, @NotNull Location location) {
        super(element, location);
        item = VersionItem.newInstance(location);
        item.setDespawnDelay(Integer.MAX_VALUE); // Makes the item entity persistent (doesn't despawn)
        item.setGravity(false);
    }

    @Override
    public double offsetPerLine() {
        return -0.5;
    }

    @Override
    public boolean isDead() {
        return item.isDestroyed();
    }

    @Override
    public @NotNull Location getLocation() {
        return item.getLocation();
    }

    @Override
    public void setLocation(@NotNull Location location) {
        item.setLocation(location.subtract(0, 0.05, 0));
    }

    @Override
    public void updateLocation(@NotNull Player player) {
        item.updateLocation(player);
    }

    @Override
    public void spawn(@NotNull Player player) {
        CrispyItemStack elementValue = element.getFromContext(OfflinePlayer.class, player);

        item.setItemStack(elementValue);
        item.spawn(player);
        item.updateLocation(player);
        item.updateMeta(player);
    }

    @Override
    public void destroy(@NotNull Player player) {
        item.destroy(player);
    }

    @Override
    public void update(@NotNull Player player) {
        CrispyItemStack elementValue = element.getFromContext(OfflinePlayer.class, player);

        item.setItemStack(elementValue);
        item.updateMeta(player);
    }
}
