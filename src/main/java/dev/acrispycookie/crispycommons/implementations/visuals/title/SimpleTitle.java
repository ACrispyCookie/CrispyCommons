package dev.acrispycookie.crispycommons.implementations.visuals.title;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers.TitleData;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.Set;

public class SimpleTitle extends AbstractTitle {

    public SimpleTitle(TitleData data, Set<? extends Player> receivers) {
        super(data, receivers);
    }

    @Override
    protected void showPlayer(Player p) {
        showTitle(p, data.getFadeIn() * 50L, data.getDuration() * 50L, data.getFadeOut() * 50L);
    }

    @Override
    protected void updatePlayer(Player p) {

        // Last title update
        if (System.currentTimeMillis() > timeStarted + data.getDuration() * 50L - data.getSmallestPeriod() * 150L - data.getFadeOut() * 50L) {
            showTitle(p, 0, data.getSmallestPeriod() * 150L, data.getFadeOut() * 50L);
            return;
        }

        // First title update
        if (System.currentTimeMillis() < timeStarted + data.getSmallestPeriod() * 150L) {
            hidePlayer(p);
        }

        showTitle(p, 0, data.getSmallestPeriod() * 150L, 0);
    }
    private void hidePlayer(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        audience.clearTitle();
    }

    private void showTitle(Player p, long fadeIn, long duration, long fadeOut) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        Title toSend = Title.title(data.getTitle().getRaw(), data.getSubtitle().getRaw(),
                Title.Times.times(Duration.ofMillis(fadeIn), Duration.ofMillis(duration), Duration.ofMillis(fadeOut)));
        audience.showTitle(toSend);
    }
}
