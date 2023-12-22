package dev.acrispycookie.crispycommons.implementations.holograms.lines.implementations;

import com.mysql.jdbc.StringUtils;
import dev.acrispycookie.crispycommons.implementations.holograms.CrispyHologram;
import dev.acrispycookie.crispycommons.implementations.holograms.lines.AbstractHologramLine;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.TextElement;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class TextHologramLine extends AbstractHologramLine<TextElement, String> {

    private EntityArmorStand as = null;

    public TextHologramLine(TextElement initialLine, CrispyHologram hologram, List<Player> receivers) {
        super(initialLine, hologram, receivers);
    }

    public TextHologramLine(String staticLine, CrispyHologram hologram, List<Player> receivers) {
        super(new TextElement(staticLine), hologram, receivers);
    }

    public void display(Player player) {
        String text = getCurrentElement();

        if (StringUtils.isEmptyOrWhitespaceOnly(text)) {
            return;
        }

        Location location = hologram.getLineLocation(this);
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

    public void destroy(Player player) {
        if(as != null) {
            PacketPlayOutEntityDestroy spawn = new PacketPlayOutEntityDestroy(as.getId());
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
        }
    }

    public void update(Player player) {
        if(as != null) {
            as.setCustomName(ChatColor.translateAlternateColorCodes('&', getCurrentElement()));
            PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(as.getId(), as.getDataWatcher(), true);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
        }
    }
}
