package dev.acrispycookie.crispycommons.holograms.implementations.builders;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class CrispyClickHoloBuilder extends CrispyHologramBuilder {

    @Override
    public abstract void onClick(Player player, int lineIndex);

    public CrispyClickHoloBuilder(JavaPlugin plugin) {
        super(plugin);
    }
}
