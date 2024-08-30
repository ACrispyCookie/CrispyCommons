package dev.acrispycookie.crispycommons.v1_20_R3.utility.entity;

import com.cryptomorin.xseries.XMaterial;
import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.utility.entity.VersionItem;
import net.minecraft.core.BaseBlockPosition;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.network.protocol.game.PacketPlayOutEntityTeleport;
import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity;
import net.minecraft.world.entity.item.EntityItem;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class VersionItem_1_20_R3 extends VersionEntity_1_20_R3 implements VersionItem {

    private final EntityItem item;

    public VersionItem_1_20_R3(Location location) {
        item = new EntityItem(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ(), CraftItemStack.asNMSCopy(new CrispyItemStack(XMaterial.AIR)));
    }

    public void spawn(Player player) {
        Location location = item.getBukkitEntity().getLocation();
        BlockPosition position = new BlockPosition(new BaseBlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
        PacketPlayOutSpawnEntity spawn = new PacketPlayOutSpawnEntity(item, 2, position);
        ((CraftPlayer) player).getHandle().c.b(spawn);
    }

    public void destroy(Player player) {
        setDead(true);
        PacketPlayOutEntityDestroy spawn = new PacketPlayOutEntityDestroy(item.aj());
        ((CraftPlayer) player).getHandle().c.b(spawn);
    }

    public void updateLocation(Player player) {
        PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport(item);
        ((CraftPlayer) player).getHandle().c.b(teleport);
    }

    public void updateMeta(Player player) {
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

    public void setItemStack(ItemStack itemStack) {
        item.a(CraftItemStack.asNMSCopy(itemStack));
    }

    public void setDead(boolean dead) {
        item.al();
    }

    @Override
    public void setLocation(Location location) {
        item.a(location.getX(), location.getY(), location.getZ(), 0, 0);
    }

    @Override
    public Location getLocation() {
        return item.getBukkitEntity().getLocation();
    }

    @Override
    public boolean isDestroyed() {
        return !item.bx();
    }

    @Override
    public net.minecraft.world.entity.Entity getInternalEntity() {
        return item;
    }
}
