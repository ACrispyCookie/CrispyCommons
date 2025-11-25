package dev.acrispycookie.crispycommons.v1_8_R3.utility.nms.entity;

import dev.acrispycookie.crispycommons.utility.nms.entity.VersionItem;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class VersionItem_1_8_R3 extends VersionEntity_1_8_R3 implements VersionItem {

    private final EntityItem item;

    public VersionItem_1_8_R3(@NotNull Location location) {
        item = new EntityItem(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ());
    }

    public void spawn(@NotNull Player player) {
        PacketPlayOutSpawnEntity spawn = new PacketPlayOutSpawnEntity(item, 2, 1);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
    }

    public void destroy(@NotNull Player player) {
        setDead(true);
        PacketPlayOutEntityDestroy spawn = new PacketPlayOutEntityDestroy(item.getId());
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
    }

    public void updateLocation(@NotNull Player player) {
        PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport(item);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(teleport);
    }

    public void updateMeta(@NotNull Player player) {
        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(item.getId(), item.getDataWatcher(), true);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
    }

    @Override
    public void setGravity(boolean gravity) {

    }

    public void setDespawnDelay(int delay) {
        item.a(delay);
    }

    public void setItemStack(@NotNull ItemStack itemStack) {
        item.setItemStack(CraftItemStack.asNMSCopy(itemStack));
    }

    public void setDead(boolean dead) {
        item.dead = dead;
    }

    @Override
    public void setLocation(@NotNull Location location) {
        World newWorld = ((CraftWorld) location.getWorld()).getHandle();
        if (!item.getWorld().equals(newWorld)) {
            item.spawnIn(newWorld);
        }
        item.setLocation(location.getX(), location.getY(), location.getZ(), 0, 0);
    }

    @Override
    public @NotNull Location getLocation() {
        return item.getBukkitEntity().getLocation();
    }

    @Override
    public boolean isDestroyed() {
        return item.dead;
    }

    @Override
    public @NotNull Entity getInternalEntity() {
        return item;
    }
}
