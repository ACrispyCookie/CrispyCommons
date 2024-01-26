package dev.acrispycookie.crispycommons.implementations.visuals.actionbar;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.SimpleTextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.actionbar.AbstractCrispyActionbar;
import dev.acrispycookie.crispycommons.api.visuals.title.AbstractCrispyTitle;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SimpleActionbar extends AbstractCrispyActionbar {

    public SimpleActionbar(TextElement text, Set<? extends Player> receivers, int duration) {
        super(text, receivers, duration);
    }

    @Override
    protected void showPlayer(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        audience.sendActionBar(content.getRaw());
    }
}
