package dev.acrispycookie.crispycommons.implementations.visuals.tablist;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.wrappers.TablistData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalTextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalTextElement;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public class SimpleTablist extends AbstractTablist {

    public SimpleTablist(TablistData data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long> timeToLive) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER);
    }

    @Override
    protected void show(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        Component header = constructComponent(getHeader(), p);
        Component footer = constructComponent(getFooter(), p);
        audience.sendPlayerListHeaderAndFooter(header, footer);
    }

    @Override
    protected void hide(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        Component header = Component.empty();
        Component footer = Component.empty();
        audience.sendPlayerListHeaderAndFooter(header, footer);
    }

    @Override
    protected void perPlayerUpdate(Player p) {
        show(p);
    }

    @Override
    protected void globalUpdate() {

    }

    private Component constructComponent(List<TextElement> elements, OfflinePlayer player) {
        Component component = Component.empty();
        for (int i = 0; i < elements.size(); i++) {
            TextElement t = elements.get(i);
            Component toAdd = t instanceof PersonalTextElement ? ((PersonalTextElement) t).getRaw(player) : ((GlobalTextElement) t).getRaw();
            if (toAdd == null)
                continue;
            component = component.append(toAdd);
            if (i != elements.size() - 1) {
                component = component.appendNewline();
            }
        }

        return component;
    }
}
