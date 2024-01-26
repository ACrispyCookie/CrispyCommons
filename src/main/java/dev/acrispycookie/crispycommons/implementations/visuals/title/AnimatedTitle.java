package dev.acrispycookie.crispycommons.implementations.visuals.title;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.title.AbstractCrispyTitle;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class AnimatedTitle extends AbstractCrispyTitle {

    private int period;
    private BukkitTask bukkitTask;

    public AnimatedTitle(List<TextElement> text, Set<? extends Player> receivers, int fadeIn, int duration, int fadeOut, int period) {
        super(text, receivers, fadeIn, duration, fadeOut);
        this.period = period;
    }

    @Override
    protected void showInternal() {
        Audience audience = CrispyCommons.getBukkitAudiences().filter(cs -> (cs instanceof Player) && receivers.contains(cs));
        TextElement title = content.get(0);
        TextElement subtitle = content.get(1);
        bukkitTask = new BukkitRunnable() {
            long i = 0;
            @Override
            public void run() {
                Title toSend;
                if (i > duration) {
                    cancel();
                    isDisplayed = false;
                    return;
                } else if(i == duration) {
                    toSend = Title.title(title.getRaw(), subtitle.getRaw(), Title.Times.times(Duration.ofMillis(0), Duration.ofMillis(period * 100L), Duration.ofMillis(fadeOut * 50L)));
                } else if (i == 0) {
                    toSend = Title.title(title.getRaw(), subtitle.getRaw(), Title.Times.times(Duration.ofMillis(fadeIn * 50L), Duration.ofMillis(period * 100L), Duration.ofMillis(0)));
                } else {
                    toSend = Title.title(title.getRaw(), subtitle.getRaw(), Title.Times.times(Duration.ofMillis(0), Duration.ofMillis(period * 100L), Duration.ofMillis(0)));
                }
                audience.showTitle(toSend);
                i += period;
            }
        }.runTaskTimer(CrispyCommons.getPlugin(), 0, period);
    }

    @Override
    protected void hideInternal() {
        if (bukkitTask != null)
            bukkitTask.cancel();
        Audience audience = CrispyCommons.getBukkitAudiences().players().filterAudience(cs -> (cs instanceof Player) && receivers.contains(cs));
        audience.clearTitle();
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }
}
