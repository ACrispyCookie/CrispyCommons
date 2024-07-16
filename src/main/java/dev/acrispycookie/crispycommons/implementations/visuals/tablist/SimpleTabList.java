package dev.acrispycookie.crispycommons.implementations.visuals.tablist;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.wrappers.TabListData;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public class SimpleTabList extends AbstractTabList {

    public SimpleTabList(TabListData data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long, ?> timeToLive) {
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

    @Override
    protected void addLineInternal(int index, boolean header) {
        if (isAnyoneWatching())
            update();
    }

    @Override
    protected void removeLineInternal(int index, boolean header) {
        if (isAnyoneWatching())
            update();
    }

    @Override
    protected void updateLines(boolean header) {
        if (isAnyoneWatching())
            update();
    }

    private Component constructComponent(List<TextElement<?>> elements, OfflinePlayer player) {
        Component component = Component.empty();
        for (int i = 0; i < elements.size(); i++) {
            Component toAdd = elements.get(i).getFromContext(OfflinePlayer.class, player);
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
