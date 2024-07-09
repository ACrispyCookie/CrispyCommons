package dev.acrispycookie.crispycommons.implementations.visuals.title;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visuals.title.wrappers.TitleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalTextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalTextElement;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.Set;

public class UpdatingTitle extends AbstractTitle {

    public UpdatingTitle(TitleData data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER);
    }

    @Override
    protected void show(Player p) {
        showTitle(p, data.getFadeIn() * 50L, data.getSmallestPeriod() * 150L, 0);
    }

    @Override
    public void hide(Player p) {
        showTitle(p, 0, 1, data.getFadeOut() * 50L);
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
        Component title = data.getTitle() instanceof GlobalTextElement ?
                ((GlobalTextElement) data.getTitle()).getRaw() :
                ((PersonalTextElement) data.getTitle()).getRaw(p);
        Component subtitle = data.getSubtitle() instanceof GlobalTextElement ?
                ((GlobalTextElement) data.getSubtitle()).getRaw() :
                ((PersonalTextElement) data.getSubtitle()).getRaw(p);
        Title toSend = Title.title(title, subtitle,
                Title.Times.times(Duration.ofMillis(fadeIn), Duration.ofMillis(duration), Duration.ofMillis(fadeOut)));
        audience.showTitle(toSend);
    }
}
