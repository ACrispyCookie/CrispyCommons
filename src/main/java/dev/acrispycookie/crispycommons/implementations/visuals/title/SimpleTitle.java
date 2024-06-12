package dev.acrispycookie.crispycommons.implementations.visuals.title;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers.TitleData;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.title.Title;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.Set;

public class SimpleTitle extends AbstractTitle {

    public SimpleTitle(TitleData data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER);
    }

    @Override
    protected void show(Player p) {
        showTitle(p, data.getFadeIn() * 50L, timeToLive * 50L, data.getFadeOut() * 50L);
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

    private void showTitle(Player p, long fadeIn, long duration, long fadeOut) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        Title toSend = Title.title(data.getTitle().getRaw(), data.getSubtitle().getRaw(),
                Title.Times.times(Duration.ofMillis(fadeIn), Duration.ofMillis(duration), Duration.ofMillis(fadeOut)));
        audience.showTitle(toSend);
    }
}
