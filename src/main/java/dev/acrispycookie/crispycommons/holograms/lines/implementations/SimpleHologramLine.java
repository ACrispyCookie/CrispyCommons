package dev.acrispycookie.crispycommons.holograms.lines.implementations;

import com.mysql.jdbc.StringUtils;
import dev.acrispycookie.crispycommons.holograms.lines.AbstractHologramLine;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import sun.java2d.pipe.SpanShapeRenderer;

public class SimpleHologramLine extends AbstractHologramLine {

    private EntityArmorStand as = null;

    public SimpleHologramLine(String initialLine) {
        super(initialLine);
    }

    @Override
    public void display() {
        receivers.forEach(this::display);
    }

    @Override
    public void destroy() {
        receivers.forEach(this::destroy);
    }

    @Override
    public void update() { receivers.forEach(this::update); }

    public void display(Player player) {
        String text = getCurrentText();

        if (StringUtils.isEmptyOrWhitespaceOnly(text)) {
            return;
        }

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
            as.setCustomName(ChatColor.translateAlternateColorCodes('&', getCurrentText()));
            PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(as.getId(), as.getDataWatcher(), true);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
        }
    }
}
