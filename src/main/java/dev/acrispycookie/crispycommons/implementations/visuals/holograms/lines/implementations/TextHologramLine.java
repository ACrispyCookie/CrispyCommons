package dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines.implementations;

import com.mysql.jdbc.StringUtils;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.SimpleTextElement;
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

import java.util.Collection;

public class TextHologramLine extends ClickableHologramLine<TextElement, String> {

    private EntityArmorStand as = null;

    public TextHologramLine(String staticLine, Collection<? extends Player> receivers) {
        super(new SimpleTextElement(staticLine), receivers);
    }

    public TextHologramLine(Collection<? extends String> frames, int period, Collection<? extends Player> receivers) {
        super(null, receivers);
        element = new TextElement(frames, period) {
            @Override
            protected void update() {
                TextHologramLine.this.update();
            }
        };
    }

    @Override
    public Location getLocation() {
        int index = hologram.getLines().indexOf(this);
        Location location = hologram.getLocation().clone();
        location.subtract(0, index * 0.25, 0);
        return location;
    }

    protected void display(Player player) {
        String text = getCurrentContent();

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

    protected void destroy(Player player) {
        if(as != null) {
            PacketPlayOutEntityDestroy spawn = new PacketPlayOutEntityDestroy(as.getId());
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
        }
    }

    protected void update(Player player) {
        if(as != null) {
            as.setCustomName(ChatColor.translateAlternateColorCodes('&', getCurrentContent()));
            PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(as.getId(), as.getDataWatcher(), true);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
        }
    }
}
