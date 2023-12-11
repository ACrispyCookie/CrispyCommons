package dev.acrispycookie.crispycommons.holograms;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;

public abstract class CrispyHologramImpl implements CrispyHologram{

    ArrayList<Player> receiverList;
    protected abstract void displayToPlayer(Player player, int tickLifetime);
    protected abstract void hideFromPlayer(Player player);

    public CrispyHologramImpl(Collection<? extends Player> receiverList) {
        this.receiverList = new ArrayList<>(receiverList);
    }

    public CrispyHologramImpl(Player receiver) {
        receiverList = new ArrayList<>();
        receiverList.add(receiver);
    }


    @Override
    public void display() {
        receiverList.forEach((p) -> displayToPlayer(p, -1));
    }

    @Override
    public void display(int tickLifetime) {
        receiverList.forEach((p) -> displayToPlayer(p, tickLifetime));
    }

    @Override
    public void destroy() {
        receiverList.forEach(this::hideFromPlayer);
    }
}
