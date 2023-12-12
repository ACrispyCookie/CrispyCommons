package dev.acrispycookie.crispycommons.holograms;

import com.mysql.jdbc.StringUtils;
import dev.acrispycookie.crispycommons.holograms.text.HologramText;
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

public abstract class CrispyHologramImpl implements CrispyHologram {

    protected final JavaPlugin plugin;
    protected final ArrayList<Player> receiverList;
    protected HologramText text;
    protected int tickLifetime;
    protected final Location location;
    protected abstract void displayToPlayer(Player player);
    protected abstract void hideFromPlayer(Player player);
    protected abstract void handleTextChange();
    public abstract void onClick(Player player, int lineIndex);

    public CrispyHologramImpl(JavaPlugin plugin, Collection<? extends Player> receiverList, HologramText text, Location location, int tickLifetime) {
        this.plugin = plugin;
        this.receiverList = new ArrayList<>(receiverList);
        this.text = text;
        this.location = location;
        this.tickLifetime = tickLifetime;

        if(tickLifetime != -1) {
            Bukkit.getScheduler().runTaskLater(plugin, this::destroy, tickLifetime);
        }
    }

    public CrispyHologramImpl(JavaPlugin plugin, Player receiver, HologramText text, Location location, int tickLifetime) {
        this.plugin = plugin;
        this.receiverList = new ArrayList<>();
        this.receiverList.add(receiver);
        this.text = text;
        this.location = location;
        this.tickLifetime = tickLifetime;

        if(tickLifetime != -1) {
            Bukkit.getScheduler().runTaskLater(plugin, this::destroy, tickLifetime);
        }
    }


    @Override
    public void display() {
        receiverList.forEach(this::displayToPlayer);
    }

    @Override
    public void destroy() {
        receiverList.forEach(this::hideFromPlayer);
    }

    public void changeText(HologramText text) {
        this.text = text;
        handleTextChange();
    }

    public void addPlayer(Player player) {
        receiverList.add(player);
        displayToPlayer(player);
    }

    public void removePlayer(Player player) {
        receiverList.remove(player);
        hideFromPlayer(player);
    }
}
