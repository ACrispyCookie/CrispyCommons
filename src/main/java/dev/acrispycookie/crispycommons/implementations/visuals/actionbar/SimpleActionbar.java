package dev.acrispycookie.crispycommons.implementations.visuals.actionbar;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.visuals.actionbar.wrappers.ActionbarData;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public class SimpleActionbar extends AbstractActionbar {

    public SimpleActionbar(ActionbarData data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long, ?> timeToLive) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER);
    }

    @Override
    protected void show(Player p) {
        Component text = data.getText().getFromContext(OfflinePlayer.class, p);
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        audience.sendActionBar(text);
    }

    @Override
    protected void hide(Player p) {

    }

    @Override
    protected void perPlayerUpdate(Player p) {
        show(p);
    }

    @Override
    protected void globalUpdate() { }
}
