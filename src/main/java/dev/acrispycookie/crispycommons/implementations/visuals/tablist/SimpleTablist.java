package dev.acrispycookie.crispycommons.implementations.visuals.tablist;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.wrappers.TablistData;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.Set;

public class SimpleTablist extends AbstractTablist {

    public SimpleTablist(TablistData data, Set<? extends Player> receivers) {
        super(data, receivers);
    }

    @Override
    protected void show(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().filter(cs -> (cs instanceof Player && receivers.contains(((Player) cs))));
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
        Audience audience = CrispyCommons.getBukkitAudiences().filter(cs -> (cs instanceof Player && receivers.contains(((Player) cs))));
        Component header = Component.empty();
        Component footer = Component.empty();
        audience.sendPlayerListHeaderAndFooter(header, footer);
    }

    @Override
    protected void update(Player p) {
        show(p);
    }
}
