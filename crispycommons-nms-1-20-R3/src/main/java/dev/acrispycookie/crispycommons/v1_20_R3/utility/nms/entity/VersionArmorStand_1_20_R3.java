package dev.acrispycookie.crispycommons.v1_20_R3.utility.nms.entity;

import dev.acrispycookie.crispycommons.utility.nms.entity.VersionArmorStand;
import dev.acrispycookie.crispycommons.utility.nms.entity.VersionEntity;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class VersionArmorStand_1_20_R3 extends VersionEntity_1_20_R3 implements VersionArmorStand {

    private final EntityArmorStand armorStand;

    public VersionArmorStand_1_20_R3(@NotNull Location location) {
        CraftWorld world = ((CraftWorld) location.getWorld());
        assert world != null : "CraftWorld was null. Contact developer.";
        armorStand = new EntityArmorStand(world.getHandle(), location.getX(), location.getY(), location.getZ());
    }

    @Override
    public void setInvisible(boolean invisible) {
        armorStand.j(invisible);
    }

    @Override
    public void setNoClip(boolean noClip) {
        armorStand.u(noClip);
    }

    @Override
    public void setBasePlate(boolean basePlate) {
        armorStand.s(!basePlate);
    }

    @Override
    public void setGravity(boolean gravity) {
        armorStand.e(gravity);
    }

    @Override
    public void setCustomNameVisible(boolean visible) {
        armorStand.n(visible);
    }

    @Override
    public void setSmall(boolean small) {
        armorStand.t(small);
    }

    @Override
    public void setCustomName(@NotNull String name) {
        armorStand.b(IChatBaseComponent.b(name));
    }

    @Override
    public @NotNull String getCustomName() {
        IChatBaseComponent component = armorStand.Q_();
        if (component != null)
            return component.getString();
        return " ";
    }

    @Override
    public void attachEntity(@NotNull Player player, @NotNull VersionEntity versionEntity) {
        PacketPlayOutAttachEntity attach = new PacketPlayOutAttachEntity(((VersionEntity_1_20_R3) versionEntity).getInternalEntity(), armorStand);
        ((CraftPlayer) player).getHandle().c.b(attach);
    }

    public void spawn(@NotNull Player player) {
        PacketPlayOutSpawnEntity spawnArmorStand = new PacketPlayOutSpawnEntity(armorStand);
        ((CraftPlayer) player).getHandle().c.b(spawnArmorStand);
    }

    public void destroy(@NotNull Player player) {
        setDead(true);
        PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(armorStand.aj());
        ((CraftPlayer) player).getHandle().c.b(destroy);
    }

    public void updateLocation(@NotNull Player player) {
        PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport(armorStand);
        ((CraftPlayer) player).getHandle().c.b(teleport);
    }

    public void updateMeta(@NotNull Player player) {
        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(armorStand.aj(), armorStand.an().c());
        ((CraftPlayer) player).getHandle().c.b(metadata);
    }

    public void setDead(boolean dead) {
        if (dead)
            armorStand.al();
    }

    public void setLocation(@NotNull Location location) {
        CraftWorld world = ((CraftWorld) location.getWorld());
        assert world != null : "CraftWorld was null. Contact developer.";
        armorStand.a(world.getHandle(), location.getX(), location.getY(), location.getZ(), new HashSet<>(), (float) 0, (float) 0);
    }

    public @NotNull Location getLocation() {
        return armorStand.getBukkitEntity().getLocation();
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public @NotNull Entity getInternalEntity() {
        return armorStand;
    }
}
