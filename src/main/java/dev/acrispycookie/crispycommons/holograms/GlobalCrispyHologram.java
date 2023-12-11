package dev.acrispycookie.crispycommons.holograms;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GlobalCrispyHologram extends CrispyHologramImpl {


    public GlobalCrispyHologram() {
        super(Bukkit.getOnlinePlayers());
    }

    @Override
    protected void displayToPlayer(Player player, int tickLifetime) {

    }

    @Override
    protected void hideFromPlayer(Player player) {

    }
}
