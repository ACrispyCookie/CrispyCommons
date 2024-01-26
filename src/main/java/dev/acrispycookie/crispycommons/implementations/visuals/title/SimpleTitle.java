package dev.acrispycookie.crispycommons.implementations.visuals.title;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.title.AbstractCrispyTitle;
import dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers.TitleData;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.Set;

public class SimpleTitle extends AbstractCrispyTitle  {

    public SimpleTitle(TitleData data, Set<? extends Player> receivers) {
        super(data, receivers);
    }

    @Override
    protected void showInternal() {
        Audience audience = CrispyCommons.getBukkitAudiences().filter(cs -> (cs instanceof Player) && receivers.contains(cs));
        TextElement title = data.getTitle();
        TextElement subtitle = data.getSubtitle();
        Title toSend = Title.title(title.getRaw(), subtitle.getRaw(), Title.Times.times(Duration.ofMillis(getFadeIn() * 50L), Duration.ofMillis(getDuration() * 50L), Duration.ofMillis(getFadeOut() * 50L)));
        audience.showTitle(toSend);
    }

    @Override
    protected void hideInternal() {

    }
}
