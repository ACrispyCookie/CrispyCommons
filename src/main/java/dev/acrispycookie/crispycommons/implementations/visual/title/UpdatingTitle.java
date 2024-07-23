package dev.acrispycookie.crispycommons.implementations.visual.title;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visual.title.data.TitleData;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.title.Title;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.Set;

public class UpdatingTitle extends AbstractTitle {

    public UpdatingTitle(TitleData data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER, isPublic);
    }

    @Override
    protected void show(Player p) {
        showTitle(p, data.getFadeIn().getFromContext(OfflinePlayer.class, p) * 50L, data.getSmallestPeriod() * 150L, 0);
    }

    @Override
    public void hide(Player p) {
        showTitle(p, 0, 1, data.getFadeOut().getFromContext(OfflinePlayer.class, p) * 50L);
    }

    @Override
    protected void perPlayerUpdate(Player p) {
        showTitle(p, 0, data.getSmallestPeriod() * 150L, 0);
    }

    @Override
    protected void globalUpdate() {

    }

    private void showTitle(Player p, long fadeIn, long duration, long fadeOut) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        Title toSend = Title.title(
                data.getTitle().getFromContext(OfflinePlayer.class, p),
                data.getSubtitle().getFromContext(OfflinePlayer.class, p),
                Title.Times.times(
                        Duration.ofMillis(fadeIn),
                        Duration.ofMillis(duration),
                        Duration.ofMillis(fadeOut)
                )
        );
        audience.showTitle(toSend);
    }
}
