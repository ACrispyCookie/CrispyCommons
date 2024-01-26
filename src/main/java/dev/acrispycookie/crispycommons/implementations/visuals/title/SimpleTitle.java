package dev.acrispycookie.crispycommons.implementations.visuals.title;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.title.AbstractCrispyTitle;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SimpleTitle extends AbstractCrispyTitle  {

    public SimpleTitle(List<SimpleTextElement> text, Set<? extends Player> receivers, int fadeIn, int duration, int fadeOut) {
        super(new ArrayList<>(text), receivers, fadeIn, duration, fadeOut);
    }

    @Override
    protected void showInternal() {
        Audience audience = CrispyCommons.getBukkitAudiences().filter(cs -> (cs instanceof Player) && receivers.contains(cs));
        TextElement title = content.get(0);
        TextElement subtitle = content.get(1);
        Title toSend = Title.title(title.getRaw(), subtitle.getRaw(), Title.Times.times(Duration.ofMillis(fadeIn * 50L), Duration.ofMillis(duration * 50L), Duration.ofMillis(fadeOut * 50L)));
        audience.showTitle(toSend);
    }

    @Override
    protected void hideInternal() {

    }
}
