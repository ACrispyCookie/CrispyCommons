package dev.acrispycookie.crispycommons.implementations.visuals.tablist;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.wrappers.TablistData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TextElement;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public class SimpleTablist extends AbstractTablist {

    public SimpleTablist(TablistData data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER);
    }

    @Override
    protected void show(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().filter(cs -> (cs instanceof Player && getPlayers().contains(((Player) cs))));
        Component header = Component.empty();
        Component footer = Component.empty();
        for (int i = 0; i < getHeader().size(); i++) {
            TextElement t = getHeader().get(i);
            header = header.append(t.getRaw());
            if (i != getHeader().size() - 1) {
                header = header.appendNewline();
            }
        }
        for (int i = 0; i < getFooter().size(); i++) {
            TextElement t = getFooter().get(i);
            footer = footer.append(t.getRaw());
            if (i != getFooter().size() - 1) {
                footer = footer.appendNewline();
            }
        }
        audience.sendPlayerListHeaderAndFooter(header, footer);
    }

    @Override
    protected void hide(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().filter(cs -> (cs instanceof Player && getPlayers().contains(((Player) cs))));
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
}
