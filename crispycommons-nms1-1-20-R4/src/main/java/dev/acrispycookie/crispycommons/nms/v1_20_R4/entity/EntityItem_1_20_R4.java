package dev.acrispycookie.crispycommons.nms.v1_20_R4.entity;

import dev.acrispycookie.crispycommons.nms.wrappers.entity.EntityItem;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.network.protocol.game.PacketPlayOutEntityTeleport;
import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R4.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R4.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EntityItem_1_20_R4 extends Entity_1_20_R4 implements EntityItem {

    private net.minecraft.world.entity.item.EntityItem item;

    public void init(Location location, ItemStack stack) {
        item = new net.minecraft.world.entity.item.EntityItem(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ(), CraftItemStack.asNMSCopy(stack));
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
    public net.minecraft.world.entity.Entity getInternalEntity() {
        return item;
    }
}
