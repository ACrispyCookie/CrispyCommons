package dev.acrispycookie.crispycommons.implementations.wrappers.entity;

import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.ItemElement;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;

public class ItemEntity extends ClickableEntity<ItemElement<?>> {

    private EntityItem ei;

    public ItemEntity(ItemElement<?> element) {
        super(element);
    }

    @Override
    public double offsetPerLine() {
        return 0;
    }

    @Override
    public void spawn(Location location, Player player) {
        CrispyItemStack elementValue = element.getFromContext(OfflinePlayer.class, player);

        if (ei == null) {
            ei = new EntityItem(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ());
            ei.setItemStack(CraftItemStack.asNMSCopy(elementValue));
            ei.setCustomNameVisible(true);
        }

        PacketPlayOutSpawnEntity spawn = new PacketPlayOutSpawnEntity(ei, 78);
        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(ei.getId(), ei.getDataWatcher(), true);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
    }

    @Override
    public void destroy(Player player) {

    }

    @Override
    public void update(Location location, Player player) {

    }

    @Override
    public Location getLocation() {

        return null;
    }
}
