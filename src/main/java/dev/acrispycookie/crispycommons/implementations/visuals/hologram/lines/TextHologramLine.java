package dev.acrispycookie.crispycommons.implementations.visuals.hologram.lines;

import com.mysql.jdbc.StringUtils;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.AbstractHologramLine;
import dev.acrispycookie.crispycommons.api.visuals.hologram.HologramLine;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class TextHologramLine extends ClickableHologramLine<TextElement> {

    private EntityArmorStand as = null;

    public TextHologramLine(TextElement element) {
        super(element);
        this.data.getElement().setUpdate(this::update);
    }

    @Override
    public Location getLocation() {
        int index = 0;
        List<AbstractHologramLine<?>> lines = data.getHologram().getData().getLines();
        for (HologramLine<?> line : lines) {
            if (line.equals(this)) {
                break;
            }
            if (line.isDisplayed()) {
                index++;
            }
        }

        Location location = data.getHologram().getLocation().clone();
        location.subtract(0, index * 0.25, 0);
        return location;
    }

    public void show(Player player) {
        if(isDisplayed || data.getHologram() == null || !data.getHologram().getPlayers().contains(player))
            return;

        String text = LegacyComponentSerializer.legacyAmpersand().serialize(data.getElement().getRaw());

        if (StringUtils.isEmptyOrWhitespaceOnly(text)) {
            return;
        }

        Location location = getLocation();
        as = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ());
        as.setInvisible(true);
        as.setGravity(false);
        as.setCustomNameVisible(true);
        as.setCustomName(ChatColor.translateAlternateColorCodes('&', text));
        as.setSmall(true);

        PacketPlayOutSpawnEntity spawn = new PacketPlayOutSpawnEntity(as, 78);
        PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(as.getId(), as.getDataWatcher(), true);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
    }

    public void hide(Player player) {
        if(!isDisplayed || data.getHologram() == null || !data.getHologram().getPlayers().contains(player))
            return;

        if(as != null) {
            PacketPlayOutEntityDestroy spawn = new PacketPlayOutEntityDestroy(as.getId());
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
        }
    }

    public void update(Player player) {
        if(!isDisplayed || data.getHologram() == null || !data.getHologram().getPlayers().contains(player))
            return;

        String content = LegacyComponentSerializer.legacyAmpersand().serialize(this.data.getElement().getRaw());
        String name = StringUtils.isEmptyOrWhitespaceOnly(content) ? " " : ChatColor.translateAlternateColorCodes('&', content);

        if(as != null) {
            Location location = getLocation();
            as.setCustomName(name);
            as.setLocation(location.getX(), location.getY(), location.getZ(), 0, 0);
            PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(as.getId(), as.getDataWatcher(), true);
            PacketPlayOutEntityTeleport teleport = new PacketPlayOutEntityTeleport(as);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(teleport);
        }
    }
}
