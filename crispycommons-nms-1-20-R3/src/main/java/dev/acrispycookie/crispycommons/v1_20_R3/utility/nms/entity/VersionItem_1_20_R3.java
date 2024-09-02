package dev.acrispycookie.crispycommons.v1_20_R3.utility.nms.entity;

import com.cryptomorin.xseries.XMaterial;
import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.utility.nms.entity.VersionItem;
import net.minecraft.core.BaseBlockPosition;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.network.protocol.game.PacketPlayOutEntityTeleport;
import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.EntityItem;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class VersionItem_1_20_R3 extends VersionEntity_1_20_R3 implements VersionItem {

    private final EntityItem item;

    public VersionItem_1_20_R3(@NotNull Location location) {
        CraftWorld world = ((CraftWorld) location.getWorld());
        assert world != null : "CraftWorld was null. Contact developer.";
        item = new EntityItem(world.getHandle(), location.getX(), location.getY(), location.getZ(), CraftItemStack.asNMSCopy(new CrispyItemStack(XMaterial.AIR)));
    }

    public void spawn(@NotNull Player player) {
        Location location = item.getBukkitEntity().getLocation();
        BlockPosition position = new BlockPosition(new BaseBlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
        PacketPlayOutSpawnEntity spawn = new PacketPlayOutSpawnEntity(item, 2, position);
        ((CraftPlayer) player).getHandle().c.b(spawn);
    }

    public void destroy(@NotNull Player player) {
        setDead(true);
        PacketPlayOutEntityDestroy spawn = new PacketPlayOutEntityDestroy(item.aj());
        ((CraftPlayer) player).getHandle().c.b(spawn);
    }

    public void updateLocation(@NotNull Player player) {
        PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport(item);
        ((CraftPlayer) player).getHandle().c.b(teleport);
    }

    public void updateMeta(@NotNull Player player) {
        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(item.aj(), item.an().c());
        ((CraftPlayer) player).getHandle().c.b(metadata);
    }

    @Override
    public void setGravity(boolean gravity) {
        item.e(!gravity);
    }

    public void setDespawnDelay(int delay) {
        item.a(delay);
    }

    public void setItemStack(@NotNull ItemStack itemStack) {
        item.a(CraftItemStack.asNMSCopy(itemStack));
    }

    public void setDead(boolean dead) {
        if (dead)
            item.al();
    }

    @Override
    public void setLocation(@NotNull Location location) {
        CraftWorld world = ((CraftWorld) location.getWorld());
        assert world != null : "CraftWorld was null. Contact developer.";
        item.a(world.getHandle(), location.getX(), location.getY(), location.getZ(), new HashSet<>(), (float) 0, (float) 0);
    }

    @Override
    public @NotNull Location getLocation() {
        return item.getBukkitEntity().getLocation();
    }

    @Override
    public boolean isDestroyed() {
        return !item.bx();
    }

    @Override
    public @NotNull Entity getInternalEntity() {
        return item;
    }
}
