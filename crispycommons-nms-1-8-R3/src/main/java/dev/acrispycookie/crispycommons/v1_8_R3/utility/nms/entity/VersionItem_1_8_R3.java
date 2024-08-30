package dev.acrispycookie.crispycommons.v1_8_R3.utility.nms.entity;

import dev.acrispycookie.crispycommons.utility.nms.entity.VersionItem;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class VersionItem_1_8_R3 extends VersionEntity_1_8_R3 implements VersionItem {

    private final EntityItem item;

    public VersionItem_1_8_R3(Location location) {
        item = new EntityItem(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ());
    }

    public void spawn(Player player) {
        PacketPlayOutSpawnEntity spawn = new PacketPlayOutSpawnEntity(item, 2, 1);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
    }

    public void destroy(Player player) {
        setDead(true);
        PacketPlayOutEntityDestroy spawn = new PacketPlayOutEntityDestroy(item.getId());
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
    }

    public void updateLocation(Player player) {
        PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport(item);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(teleport);
    }

    public void updateMeta(Player player) {
        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(item.getId(), item.getDataWatcher(), true);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
    }

    @Override
    public void setGravity(boolean gravity) {

    }

    public void setDespawnDelay(int delay) {
        item.a(delay);
    }

    public void setItemStack(ItemStack itemStack) {
        item.setItemStack(CraftItemStack.asNMSCopy(itemStack));
    }

    public void setDead(boolean dead) {
        item.dead = dead;
    }

    @Override
    public void setLocation(Location location) {
        item.setLocation(location.getX(), location.getY(), location.getZ(), 0, 0);
    }

    @Override
    public Location getLocation() {
        return item.getBukkitEntity().getLocation();
    }

    @Override
    public boolean isDestroyed() {
        return item.dead;
    }

    @Override
    public Entity getInternalEntity() {
        return item;
    }
}
