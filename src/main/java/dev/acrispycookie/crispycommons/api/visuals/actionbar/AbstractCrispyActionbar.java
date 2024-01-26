package dev.acrispycookie.crispycommons.api.visuals.actionbar;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractCrispyAccessibleVisual;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Set;

public abstract class AbstractCrispyActionbar extends AbstractCrispyAccessibleVisual<TextElement> implements CrispyActionbar {

    protected abstract void showPlayer(Player p);
    protected int duration;

    public AbstractCrispyActionbar(TextElement text, Set<? extends Player> receivers, int duration) {
        super(text, receivers);
        this.duration = duration;
    }

    @Override
    public void show() {
        if (isDisplayed)
            return;

        isDisplayed = true;
        content.start();
        receivers.stream().filter(Player::isOnline).forEach(this::showPlayer);
        if (duration != -1) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    hide();
                }
            }.runTaskLater(CrispyCommons.getPlugin(), duration);
        }
    }

    @Override
    public void hide() {
        if (!isDisplayed)
            return;

        isDisplayed = false;
        content.stop();
    }

    @Override
    public void update() {
        if (!isDisplayed)
            return;

        receivers.stream().filter(Player::isOnline).forEach(this::showPlayer);
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
