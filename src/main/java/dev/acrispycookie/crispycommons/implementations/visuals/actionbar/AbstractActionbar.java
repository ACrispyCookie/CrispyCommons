package dev.acrispycookie.crispycommons.implementations.visuals.actionbar;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractAccessibleVisual;
import dev.acrispycookie.crispycommons.api.visuals.actionbar.CrispyActionbar;
import dev.acrispycookie.crispycommons.implementations.visuals.actionbar.wrappers.ActionbarData;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;

public abstract class AbstractActionbar extends AbstractAccessibleVisual<ActionbarData> implements CrispyActionbar {

    protected abstract void showPlayer(Player p);

    AbstractActionbar(ActionbarData data, Set<? extends Player> receivers, long timeToLive) {
        super(data, receivers, timeToLive);
    }

    @Override
    public void show() {
        if (isDisplayed)
            return;

        isDisplayed = true;
        data.getElement().start();
        receivers.stream().filter(Player::isOnline).forEach(this::showPlayer);
        new BukkitRunnable() {
            @Override
            public void run() {
                hide();
            }
        }.runTaskLater(CrispyCommons.getPlugin(), timeToLive);
    }

    @Override
    public void hide() {
        if (!isDisplayed)
            return;

        isDisplayed = false;
        data.getElement().stop();
    }

    @Override
    public void update() {
        if (!isDisplayed)
            return;

        receivers.stream().filter(Player::isOnline).forEach(this::showPlayer);
    }
}
