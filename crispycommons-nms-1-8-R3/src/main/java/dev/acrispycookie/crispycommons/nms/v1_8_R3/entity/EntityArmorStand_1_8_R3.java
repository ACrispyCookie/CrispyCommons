package dev.acrispycookie.crispycommons.nms.v1_8_R3.entity;

import dev.acrispycookie.crispycommons.nms.wrappers.entity.Entity;
import dev.acrispycookie.crispycommons.nms.wrappers.entity.EntityArmorStand;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class EntityArmorStand_1_8_R3 extends Entity_1_8_R3 implements EntityArmorStand {

    private net.minecraft.server.v1_8_R3.EntityArmorStand armorStand;

    public void init(Location location) {
        armorStand = new net.minecraft.server.v1_8_R3.EntityArmorStand(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ());
    }

    @Override
    public void setInvisible(boolean invisible) {
        armorStand.setInvisible(invisible);
    }

    @Override
    public void setNoClip(boolean noClip) {
        armorStand.n(noClip);
    }

    @Override
    public void setBasePlate(boolean basePlate) {
        armorStand.setBasePlate(basePlate);
    }

    @Override
    public void setGravity(boolean gravity) {
        armorStand.setGravity(gravity);
    }

    @Override
    public void setCustomNameVisible(boolean visible) {
        armorStand.setCustomNameVisible(visible);
    }

    @Override
    public void setSmall(boolean small) {
        armorStand.setSmall(small);
    }

    @Override
    public void setCustomName(String name) {
        armorStand.setCustomName(name);
    }

    @Override
    public void attachEntity(Player player, Entity entity) {
        PacketPlayOutAttachEntity attach = new PacketPlayOutAttachEntity(0, ((Entity_1_8_R3) entity).getInternalEntity(), armorStand);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(attach);
    }

    public void spawn(Player player) {
        PacketPlayOutSpawnEntityLiving spawnArmorStand = new PacketPlayOutSpawnEntityLiving(armorStand);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawnArmorStand);
    }

    public void destroy(Player player) {
        setDead(true);
        PacketPlayOutEntityDestroy spawn = new PacketPlayOutEntityDestroy(armorStand.getId());
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
    }

    public void updateLocation(Player player) {
        PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport(armorStand);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(teleport);
    }

    public void updateMeta(Player player) {
        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(armorStand.getId(), armorStand.getDataWatcher(), true);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
    }

    public void setDead(boolean dead) {
        armorStand.dead = dead;
    }

    public void setLocation(Location location) {
        armorStand.setLocation(location.getX(), location.getY(), location.getZ(), 0, 0);
    }

    public Location getLocation() {
        return armorStand.getBukkitEntity().getLocation();
    }

    @Override
    public net.minecraft.server.v1_8_R3.Entity getInternalEntity() {
        return armorStand;
    }
}
