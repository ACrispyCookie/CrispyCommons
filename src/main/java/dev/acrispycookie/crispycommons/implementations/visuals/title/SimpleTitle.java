package dev.acrispycookie.crispycommons.implementations.visuals.title;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers.TitleData;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalGeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalTextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalGeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalTextElement;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.Set;

public class SimpleTitle extends AbstractTitle {

    public SimpleTitle(TitleData data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long> timeToLive) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER);
    }

    @Override
    protected void show(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        Title toSend = Title.title(
                getTitle(p.getPlayer()),
                getSubtitle(p.getPlayer()),
                Title.Times.times(
                    Duration.ofMillis(getFadeIn(p.getPlayer()) * 50L),
                    Duration.ofMillis(getTTL(p.getPlayer()) * 50L),
                    Duration.ofMillis(getFadeOut(p.getPlayer()) * 50L)
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
