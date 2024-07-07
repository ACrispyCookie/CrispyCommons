package dev.acrispycookie.crispycommons.implementations.wrappers.entity;

import com.mysql.jdbc.StringUtils;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalTextElement;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TextEntity extends ClickableEntity<GlobalTextElement> {

    private EntityArmorStand as = null;

    public TextEntity(GlobalTextElement element) {
        super(element);
    }

    @Override
    public double offsetPerLine() {
        return -0.25;
    }

    @Override
    public void spawn(Location location, Player player) {
        String text = LegacyComponentSerializer.legacyAmpersand().serialize(element.getRaw());

        if (StringUtils.isEmptyOrWhitespaceOnly(text)) {
            return;
        }

        if (as == null) {
            as = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ());
            as.setInvisible(true);
            as.setGravity(false);
            as.setCustomNameVisible(true);
            as.setCustomName(ChatColor.translateAlternateColorCodes('&', text));
            as.setSmall(true);
        }

        PacketPlayOutSpawnEntity spawn = new PacketPlayOutSpawnEntity(as, 78);
        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(as.getId(), as.getDataWatcher(), true);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
    }

    @Override
    public void destroy(Player player) {
        if(as != null) {
            as.dead = true;
            PacketPlayOutEntityDestroy spawn = new PacketPlayOutEntityDestroy(as.getId());
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
        }
    }

    @Override
    public void update(Location location, Player player) {
        String content = LegacyComponentSerializer.legacyAmpersand().serialize(element.getRaw());
        String name = StringUtils.isEmptyOrWhitespaceOnly(content) ? " " : ChatColor.translateAlternateColorCodes('&', content);

        if(as != null) {
            as.setCustomName(name);
            as.setLocation(location.getX(), location.getY(), location.getZ(), 0, 0);
            PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(as.getId(), as.getDataWatcher(), true);
            PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport(as);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(teleport);
        }
    }

    @Override
    public Location getLocation() {
        return as.getBukkitEntity().getLocation();
    }
}
