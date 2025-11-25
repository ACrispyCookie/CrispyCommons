package dev.acrispycookie.crispycommons.v1_8_R3.utility.nms.entity;

import dev.acrispycookie.crispycommons.utility.nms.entity.VersionArmorStand;
import dev.acrispycookie.crispycommons.utility.nms.entity.VersionEntity;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VersionArmorStand_1_8_R3 extends VersionEntity_1_8_R3 implements VersionArmorStand {

    private final EntityArmorStand armorStand;

    public VersionArmorStand_1_8_R3(@NotNull Location location) {
        armorStand = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ());
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
    public void setCustomName(@NotNull String name) {
        armorStand.setCustomName(name);
    }

    @Override
    public void attachEntity(@NotNull Player player, @NotNull VersionEntity versionEntity) {
        PacketPlayOutAttachEntity attach = new PacketPlayOutAttachEntity(0, ((VersionEntity_1_8_R3) versionEntity).getInternalEntity(), armorStand);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(attach);
    }

    public void spawn(@NotNull Player player) {
        PacketPlayOutSpawnEntityLiving spawnArmorStand = new PacketPlayOutSpawnEntityLiving(armorStand);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawnArmorStand);
    }

    public void destroy(@NotNull Player player) {
        setDead(true);
        PacketPlayOutEntityDestroy spawn = new PacketPlayOutEntityDestroy(armorStand.getId());
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
    }

    public void updateLocation(@NotNull Player player) {
        PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport(armorStand);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(teleport);
    }

    public void updateMeta(@NotNull Player player) {
        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(armorStand.getId(), armorStand.getDataWatcher(), true);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
    }

    public void setDead(boolean dead) {
        armorStand.dead = dead;
    }

    public void setLocation(@NotNull Location location) {
        World newWorld = ((CraftWorld) location.getWorld()).getHandle();
        if (!armorStand.getWorld().equals(newWorld))
            armorStand.spawnIn(newWorld);
        armorStand.setLocation(location.getX(), location.getY(), location.getZ(), 0, 0);
    }

    public @NotNull Location getLocation() {
        return armorStand.getBukkitEntity().getLocation();
    }

    public @NotNull String getCustomName() {
        return armorStand.getCustomName();
    }

    public boolean isDestroyed() {
        return armorStand.dead;
    }

    @Override
    public @NotNull Entity getInternalEntity() {
        return armorStand;
    }
}
