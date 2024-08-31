package dev.acrispycookie.crispycommons.v1_8_R3.implementations.entity;

import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import dev.acrispycookie.crispycommons.implementations.entity.ItemEntity;
import dev.acrispycookie.crispycommons.utility.nms.entity.VersionArmorStand;
import dev.acrispycookie.crispycommons.utility.nms.entity.VersionItem;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ItemEntity_1_8_R3 extends ItemEntity {

    /**
     * The version independent instance of a custom armor stand.
     * <p>
     * This instance manages the spawning, updating and removing of the custom armor stand with
     * the different implementations based on different versions of Minecraft.
     * </p>
     */
    private final VersionArmorStand armorStand;

    /**
     * The version independent instance of a custom item.
     * <p>
     * This instance manages the spawning, updating and removing of the custom item with
     * the different implementations based on different versions of Minecraft.
     * </p>
     */
    private final VersionItem item;

    public ItemEntity_1_8_R3(@NotNull ItemElement<?> element, @NotNull Location location) {
        super(element, location);
        armorStand = VersionArmorStand.newInstance(location);
        armorStand.setInvisible(true);
        armorStand.setNoClip(true); // Disables interaction
        armorStand.setBasePlate(true);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(false);
        armorStand.setSmall(true);

        item = VersionItem.newInstance(location);
        item.setDespawnDelay(Integer.MAX_VALUE); // Makes the item entity persistent (doesn't despawn)
    }

    @Override
    public double offsetPerLine() {
        return -0.5;
    }

    @Override
    public boolean isDead() {
        return armorStand.isDestroyed();
    }

    @Override
    public void spawn(@NotNull Player player) {
        CrispyItemStack elementValue = element.getFromContext(OfflinePlayer.class, player);

        armorStand.spawn(player);
        armorStand.updateMeta(player);
        armorStand.updateLocation(player);

        item.setItemStack(elementValue);
        item.spawn(player);
        item.updateMeta(player);
        item.updateLocation(player);
        armorStand.attachEntity(player, item);
    }

    @Override
    public void destroy(@NotNull Player player) {
        armorStand.destroy(player);
        item.destroy(player);
    }

    @Override
    public void update(@NotNull Player player) {
        CrispyItemStack itemStack = element.getFromContext(OfflinePlayer.class, player);

        item.setItemStack(itemStack);
        item.updateMeta(player);
    }

    @Override
    public @NotNull Location getLocation() {
        return item.getLocation();
    }

    @Override
    public void setLocation(@NotNull Location location, @NotNull Player player) {
        armorStand.setLocation(location.subtract(0, 0.05, 0));
        armorStand.updateLocation(player);
    }
}
