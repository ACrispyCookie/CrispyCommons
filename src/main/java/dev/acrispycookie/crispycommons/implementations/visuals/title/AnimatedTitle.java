package dev.acrispycookie.crispycommons.implementations.visuals.title;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.title.AbstractTitle;
import dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers.TitleData;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.time.Duration;
import java.util.Set;

public class AnimatedTitle extends AbstractTitle {

    private BukkitTask bukkitTask;

    public AnimatedTitle(TitleData data, Set<? extends Player> receivers) {
        super(data, receivers);
    }

    @Override
    protected void showInternal() {
        Audience audience = CrispyCommons.getBukkitAudiences().filter(cs -> (cs instanceof Player) && receivers.contains(cs));
        TextElement title = data.getTitle();
        TextElement subtitle = data.getSubtitle();
        bukkitTask = new BukkitRunnable() {
            long i = 0;
            @Override
            public void run() {
                Title toSend;
                if (i > data.getDuration()) {
                    cancel();
                    isDisplayed = false;
                    return;
                } else if(i == data.getDuration()) {
                    toSend = Title.title(title.getRaw(), subtitle.getRaw(), Title.Times.times(Duration.ofMillis(0), Duration.ofMillis(data.getPeriod() * 100L), Duration.ofMillis(data.getFadeOut() * 50L)));
                } else if (i == 0) {
                    toSend = Title.title(title.getRaw(), subtitle.getRaw(), Title.Times.times(Duration.ofMillis(data.getFadeIn() * 50L), Duration.ofMillis(data.getPeriod() * 100L), Duration.ofMillis(0)));
                } else {
                    toSend = Title.title(title.getRaw(), subtitle.getRaw(), Title.Times.times(Duration.ofMillis(0), Duration.ofMillis(data.getPeriod() * 100L), Duration.ofMillis(0)));
                }
                audience.showTitle(toSend);
                i += data.getPeriod();
            }
        }.runTaskTimer(CrispyCommons.getPlugin(), 0, data.getPeriod());
    }

    @Override
    protected void hideInternal() {
        if (bukkitTask != null)
            bukkitTask.cancel();
        Audience audience = CrispyCommons.getBukkitAudiences().players().filterAudience(cs -> (cs instanceof Player) && receivers.contains(cs));
        audience.clearTitle();
    }
}
