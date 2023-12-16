package dev.acrispycookie.crispycommons.holograms.implementations.builders;

import dev.acrispycookie.crispycommons.holograms.implementations.PublicCrispyHologram;
import dev.acrispycookie.crispycommons.holograms.implementations.SimpleCrispyHologram;
import dev.acrispycookie.crispycommons.text.CrispyText;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;

public class CrispyHologramBuilder {
    private final JavaPlugin plugin;
    private final ArrayList<Player> receiverList;
    private CrispyText text;
    private Location location;
    private int tickLifetime;
    private boolean isPublic;
    public void onClick(Player player, int lineIndex) {}

    public CrispyHologramBuilder(JavaPlugin plugin) {
        this.receiverList = new ArrayList<>();
        this.plugin = plugin;
    }

    public CrispyHologramBuilder addPlayer(Player player) {
        receiverList.add(player);
        return this;
    }

    public CrispyHologramBuilder addPlayers(Collection<? extends Player> players) {
        receiverList.addAll(players);
        return this;
    }

    public CrispyHologramBuilder text(CrispyText text) {
        this.text = text;
        return this;
    }

    public CrispyHologramBuilder location(Location location) {
        this.location = location;
        return this;
    }

    public CrispyHologramBuilder tickLifetime(int tickLifetime) {
        this.tickLifetime = tickLifetime;
        return this;
    }

    public CrispyHologramBuilder setPublic(boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    public SimpleCrispyHologram build() {
        if(isPublic)
            return new SimpleCrispyHologram(plugin, receiverList, text, location, tickLifetime) {
                @Override
                public void onClick(Player player, int lineIndex) {
                    CrispyHologramBuilder.this.onClick(player, lineIndex);
                }
            };
        else
            return new PublicCrispyHologram(plugin, text, location, tickLifetime) {
                @Override
                public void onClick(Player player, int lineIndex) {
                    CrispyHologramBuilder.this.onClick(player, lineIndex);
                }
            };
    }
}
