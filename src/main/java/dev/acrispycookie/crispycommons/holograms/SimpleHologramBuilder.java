package dev.acrispycookie.crispycommons.holograms;

import dev.acrispycookie.crispycommons.holograms.text.HologramText;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;

public class SimpleHologramBuilder {
    private final JavaPlugin plugin;
    private final ArrayList<Player> receiverList;
    private HologramText text;
    private Location location;
    private int tickLifetime;

    public SimpleHologramBuilder(JavaPlugin plugin) {
        this.receiverList = new ArrayList<>();
        this.plugin = plugin;
    }

    public SimpleHologramBuilder addPlayer(Player player) {
        receiverList.add(player);
        return this;
    }

    public SimpleHologramBuilder addPlayers(Collection<? extends Player> players) {
        receiverList.addAll(players);
        return this;
    }

    public SimpleHologramBuilder text(HologramText text) {
        this.text = text;
        return this;
    }

    public SimpleHologramBuilder location(Location location) {
        this.location = location;
        return this;
    }

    public SimpleHologramBuilder tickLifetime(int tickLifetime) {
        this.tickLifetime = tickLifetime;
        return this;
    }

    public void onClick(Player player, int lineIndex) {
        return;
    }

    public SimpleCrispyHologram build() {
        return new SimpleCrispyHologram(plugin, receiverList, text, location, tickLifetime) {
            @Override
            public void onClick(Player player, int lineIndex) {
                SimpleHologramBuilder.this.onClick(player, lineIndex);
            }
        };
    }
}
