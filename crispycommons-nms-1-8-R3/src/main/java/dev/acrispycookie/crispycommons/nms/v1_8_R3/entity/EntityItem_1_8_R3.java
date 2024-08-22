package dev.acrispycookie.crispycommons.nms.v1_8_R3.entity;

import dev.acrispycookie.crispycommons.nms.wrappers.entity.EntityItem;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EntityItem_1_8_R3 extends Entity_1_8_R3 implements EntityItem {

    private net.minecraft.server.v1_8_R3.EntityItem item;

    public void init(Location location, ItemStack stack) {
        item = new net.minecraft.server.v1_8_R3.EntityItem(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ(), CraftItemStack.asNMSCopy(stack));
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
    public Entity getInternalEntity() {
        return item;
    }
}
