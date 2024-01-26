package dev.acrispycookie.crispycommons.implementations.visuals.actionbar;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.actionbar.AbstractActionbar;
import dev.acrispycookie.crispycommons.implementations.visuals.actionbar.wrappers.ActionbarData;
import net.kyori.adventure.audience.Audience;
import org.bukkit.entity.Player;

import java.util.Set;

public class SimpleActionbar extends AbstractActionbar {

    public SimpleActionbar(ActionbarData data, Set<? extends Player> receivers) {
        super(data, receivers);
    }

    @Override
    protected void showPlayer(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        audience.sendActionBar(data.getElement().getRaw());
    }
}
