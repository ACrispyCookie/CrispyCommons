package dev.acrispycookie.crispycommons.implementations.visuals.title;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.api.visuals.title.CrispyTitle;
import dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers.TitleData;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;

public abstract class AbstractTitle extends AbstractVisual<TitleData> implements CrispyTitle {

    protected abstract void showPlayer(Player p);
    protected abstract void updatePlayer(Player p);
    protected long timeStarted;

    AbstractTitle(TitleData data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive);
    }

    @Override
    public void onShow() {
        timeStarted = System.currentTimeMillis();
        data.getTitle().start();
        data.getSubtitle().start();
        receivers.stream().filter(OfflinePlayer::isOnline).forEach(p -> showPlayer(p.getPlayer()));
        new BukkitRunnable() {
            @Override
            public void run() {
                hide();
            }
        }.runTaskLater(CrispyCommons.getPlugin(), data.getSmallestPeriod() != -1 ? timeToLive : 0);
    }

    @Override
    public void onHide() {
        data.getTitle().stop();
        data.getSubtitle().stop();
    }

    @Override
    public void onUpdate() {
        receivers.stream().filter(OfflinePlayer::isOnline).forEach(p -> updatePlayer(p.getPlayer()));
    }

    @Override
    public void setTitle(TextElement text) {
        this.data.setTitle(text);
    }

    @Override
    public void setSubtitle(TextElement text) {
        this.data.setSubtitle(text);
    }

    @Override
    public void setFadeIn(int fadeIn) {
        this.data.setFadeIn(fadeIn);
    }

    @Override
    public void setFadeOut(int fadeOut) {
        this.data.setFadeOut(fadeOut);
    }

    @Override
    public TextElement getTitle() {
        return this.data.getTitle();
    }

    @Override
    public TextElement getSubtitle() {
        return this.data.getSubtitle();
    }

    @Override
    public int getFadeIn() {
        return this.data.getFadeIn();
    }

    @Override
    public int getFadeOut() {
        return this.data.getFadeOut();
    }
}
