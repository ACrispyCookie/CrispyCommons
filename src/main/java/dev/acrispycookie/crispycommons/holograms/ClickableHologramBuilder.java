package dev.acrispycookie.crispycommons.holograms;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class ClickableHologramBuilder extends SimpleHologramBuilder {

    @Override
    public abstract void onClick(Player player, int lineIndex);

    public ClickableHologramBuilder(JavaPlugin plugin) {
        super(plugin);
    }
}
