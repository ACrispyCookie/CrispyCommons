package dev.acrispycookie.crispycommons.holograms;

import com.mysql.jdbc.StringUtils;
import dev.acrispycookie.crispycommons.text.AnimatedText;
import dev.acrispycookie.crispycommons.text.CrispyText;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;

public abstract class SimpleCrispyHologram extends CrispyHologramImpl {

    private int animationTask;
    private final ArrayList<EntityArmorStand> stands = new ArrayList<>();

    public SimpleCrispyHologram(JavaPlugin plugin, Collection<? extends Player> receiverList, CrispyText text, Location location, int tickLifetime) {
        super(plugin, receiverList, text, location, tickLifetime);
        if(text instanceof AnimatedText)
            setupAnimationTask();
        setupArmorStands(text.getCurrentLines());
    }

    public SimpleCrispyHologram(JavaPlugin plugin, Player receiver, CrispyText text, Location location, int tickLifetime) {
        super(plugin, receiver, text, location, tickLifetime);
        if(text instanceof AnimatedText)
            setupAnimationTask();
        setupArmorStands(text.getCurrentLines());
    }

     @Override
    protected void displayToPlayer(Player player) {
        spawnArmorStands(player);
    }

    @Override
    protected void hideFromPlayer(Player player) {
        if(text instanceof AnimatedText)
            Bukkit.getScheduler().cancelTask(animationTask);
        despawnArmorStands(player);
    }

    @Override
    public void update() {
        String lines = text.getCurrentText();
        String[] split = lines.split("\n");
        for(int i = 0; i < split.length; i++) {
            updateArmorStandLine(i, split[i]);
        }
    }

    @Override
    public void handleTextChange() {
        update();
        if(animationTask != -1)
            Bukkit.getScheduler().cancelTask(animationTask);
        if(text instanceof AnimatedText)
            setupAnimationTask();
    }

    private void setupArmorStands(ArrayList<String> lines) {
        for(String s : lines) {
            if(!StringUtils.isEmptyOrWhitespaceOnly(s)) {
                EntityArmorStand as = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY() - (stands.size() * 0.25), location.getZ());
                as.setInvisible(true);
                as.setGravity(false);
                as.setCustomNameVisible(true);
                as.setCustomName(ChatColor.translateAlternateColorCodes('&', s));
                as.setSmall(true);
                stands.add(as);
            }
            else {
                stands.add(null);
            }
        }
    }

    private void spawnArmorStands(Player player) {
        for(EntityArmorStand eas : stands) {
            if(eas != null) {
                PacketPlayOutSpawnEntity spawn = new PacketPlayOutSpawnEntity(eas, 78);
                PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(eas.getId(), eas.getDataWatcher(), true);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata);
            }
        }
    }

    private void despawnArmorStands(Player player) {
        for(EntityArmorStand eas : stands) {
            if(eas != null) {
                PacketPlayOutEntityDestroy spawn = new PacketPlayOutEntityDestroy(eas.getId());
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(spawn);
            }
        }
    }

    private void updateArmorStandLine(int index, String text) {
        EntityArmorStand eas = stands.get(index);
        if(eas != null) {
            eas.setCustomName(ChatColor.translateAlternateColorCodes('&', text));
            PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(eas.getId(), eas.getDataWatcher(), true);
            receiverList.forEach(player -> ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata));
        }
    }

    private void setupAnimationTask() {
        AnimatedText animatedText = (AnimatedText) this.text;
        this.animationTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this::update, 0L, animatedText.getAnimationPeriod());
    }
}
