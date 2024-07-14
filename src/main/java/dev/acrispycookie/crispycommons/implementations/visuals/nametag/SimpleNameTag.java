package dev.acrispycookie.crispycommons.implementations.visuals.nametag;

import dev.acrispycookie.crispycommons.api.visuals.hologram.CrispyHologram;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.context.NameTagContext;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.NameTagElement;
import dev.acrispycookie.crispycommons.utility.elements.ContextMap;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public class SimpleNameTag extends AbstractNameTag {

    private CrispyHologram mainNameHologram;
    private CrispyHologram aboveNameHologram;
    private CrispyHologram belowNameHologram;

    public SimpleNameTag(NameTagData data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long, ?> timeToLive) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER);
    }

    @Override
    protected void show(Player p) {
        if (!p.canSee(data.getPlayer().getFromContext(OfflinePlayer.class, p)))
            return;

    }

    @Override
    protected void hide(Player p) {

    }

    @Override
    protected void perPlayerUpdate(Player p) {
        if (!p.canSee(data.getPlayer().getFromContext(OfflinePlayer.class, p)))
            return;

    }

    @Override
    protected void globalUpdate() {

    }

    private String getElement(NameTagElement<?> element, Player player, Player receiver) {
        ContextMap co = new ContextMap()
                .add(OfflinePlayer.class, player)
                .add(NameTagContext.class, new NameTagContext(player, receiver));
        return element.getFromContext(co);
    }
}
