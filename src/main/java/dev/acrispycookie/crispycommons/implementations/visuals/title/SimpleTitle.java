package dev.acrispycookie.crispycommons.implementations.visuals.title;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers.TitleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TimeToLiveElement;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.title.Title;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.Set;

public class SimpleTitle extends AbstractTitle {

    public SimpleTitle(TitleData data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER, isPublic);
    }

    @Override
    protected void show(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        Title toSend = Title.title(
                data.getTitle().getFromContext(OfflinePlayer.class, p),
                data.getSubtitle().getFromContext(OfflinePlayer.class, p),
                Title.Times.times(
                    Duration.ofMillis(data.getFadeIn().getFromContext(OfflinePlayer.class, p) * 50L),
                    Duration.ofMillis(timeToLive.getElement().getFromContext(OfflinePlayer.class, p) * 50L),
                    Duration.ofMillis(data.getFadeOut().getFromContext(OfflinePlayer.class, p) * 50L)
                )
        );
        audience.showTitle(toSend);
    }

    @Override
    public void hide(Player p) {

    }

    @Override
    protected void perPlayerUpdate(Player p) {

    }

    @Override
    protected void globalUpdate() {

    }
}
