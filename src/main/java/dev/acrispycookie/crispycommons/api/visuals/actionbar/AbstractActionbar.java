package dev.acrispycookie.crispycommons.api.visuals.actionbar;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.actionbar.wrappers.ActionbarData;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;

public abstract class AbstractActionbar extends AbstractAccessibleVisual<ActionbarData> implements CrispyActionbar {

    protected abstract void showPlayer(Player p);

    public AbstractActionbar(ActionbarData data, Set<? extends Player> receivers) {
        super(data, receivers);
    }

    @Override
    public void show() {
        if (isDisplayed)
            return;

        isDisplayed = true;
        data.getElement().start();
        receivers.stream().filter(Player::isOnline).forEach(this::showPlayer);
        if (data.getDuration() != -1) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    hide();
                }
            }.runTaskLater(CrispyCommons.getPlugin(), data.getDuration());
        }
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

    @Override
    public void setDuration(int duration) {
        data.setDuration(duration);
    }
}
