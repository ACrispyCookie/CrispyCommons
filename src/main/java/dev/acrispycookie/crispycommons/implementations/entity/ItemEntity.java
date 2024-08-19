package dev.acrispycookie.crispycommons.implementations.entity;

import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ItemEntity extends ClickableEntity<ItemElement<?>> {

    private EntityItem ei = null;
    private EntityArmorStand as = null;

    public ItemEntity(ItemElement<?> element) {
        super(element);
    }

    @Override
    public double offsetPerLine() {
        return -0.25;
    }

    @Override
    public void spawn(@NotNull Location location, @NotNull Player player) {
        if (as == null) {
            as = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ());
            as.setInvisible(true);
            as.n(true);
            as.setBasePlate(true);
            as.setGravity(false);
            as.setCustomNameVisible(false);
            as.setSmall(true);
        }

        PacketPlayOutSpawnEntityLiving spawnArmorStand = new PacketPlayOutSpawnEntityLiving(as);
        PacketPlayOutEntityMetadata metadataArmorStand = new PacketPlayOutEntityMetadata(as.getId(), as.getDataWatcher(), true);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawnArmorStand);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadataArmorStand);

        CrispyItemStack elementValue = element.getFromContext(OfflinePlayer.class, player);

        if (ei == null) {
            ei = new EntityItem(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ(), CraftItemStack.asNMSCopy(elementValue));
            ei.a(Integer.MAX_VALUE);
        }

        PacketPlayOutSpawnEntity spawn = new PacketPlayOutSpawnEntity(ei, 2, 1);
        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(ei.getId(), ei.getDataWatcher(), true);
        PacketPlayOutAttachEntity attach = new PacketPlayOutAttachEntity(0, ei, as);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(attach);
    }

    @Override
    public void destroy(@NotNull Player player) {
        if(as != null) {
            as.dead = true;
            PacketPlayOutEntityDestroy spawn = new PacketPlayOutEntityDestroy(as.getId());
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
        }
        if (ei != null) {
            ei.dead = true;
            PacketPlayOutEntityDestroy spawn = new PacketPlayOutEntityDestroy(ei.getId());
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
        }
    }

    @Override
    public void update(@NotNull Location location, @NotNull Player player) {
        CrispyItemStack item = element.getFromContext(OfflinePlayer.class, player);

        if(as != null && ei != null) {
            ei.setItemStack(CraftItemStack.asNMSCopy(item));
            as.setLocation(location.getX(), location.getY(), location.getZ(), 0, 0);
            PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(ei.getId(), ei.getDataWatcher(), true);
            PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport(as);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(teleport);
        }
    }

    @Override
    public @NotNull Location getLocation() {
        return as.getBukkitEntity().getLocation();
    }
}
