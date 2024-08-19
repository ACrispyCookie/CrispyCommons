package dev.acrispycookie.crispycommons.implementations.entity;

import com.mysql.jdbc.StringUtils;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TextEntity extends ClickableEntity<TextElement<?>> {

    private EntityArmorStand as = null;

    public TextEntity(TextElement<?> element) {
        super(element);
    }

    @Override
    public double offsetPerLine() {
        return -0.23;
    }

    @Override
    public void spawn(@NotNull Location location, @NotNull Player player) {
        Component elementValue = element.getFromContext(OfflinePlayer.class, player);
        String text = LegacyComponentSerializer.legacyAmpersand().serialize(
                elementValue == null ? Component.text("") : elementValue
        );

        if (StringUtils.isEmptyOrWhitespaceOnly(text)) {
            return;
        }

        if (as == null) {
            as = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ());
            as.setInvisible(true);
            as.n(true);
            as.setBasePlate(true);
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
    public void destroy(@NotNull Player player) {
        if(as != null) {
            as.dead = true;
            PacketPlayOutEntityDestroy spawn = new PacketPlayOutEntityDestroy(as.getId());
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
        }
    }

    @Override
    public void update(@NotNull Location location, @NotNull Player player) {
        Component text = element.getFromContext(OfflinePlayer.class, player);
        String content = LegacyComponentSerializer.legacyAmpersand().serialize(text);

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
    public @NotNull Location getLocation() {
        return as.getBukkitEntity().getLocation();
    }
}
