package dev.acrispycookie.crispycommons.implementations.visuals.bossbar;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.wrappers.BossbarData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalTextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalTextElement;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Set;

public class SimpleBossbar extends AbstractBossbar {

    private final HashMap<OfflinePlayer, BossBar> bossBars = new HashMap<>();

    public SimpleBossbar(BossbarData data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive);
    }

    @Override
    protected void show(Player p) {
        bossBars.put(p, constructBar(p));
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        audience.showBossBar(bossBars.get(p));
    }

    @Override
    protected void hide(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        audience.hideBossBar(bossBars.get(p));
    }

    @Override
    protected void perPlayerUpdate(Player p) {
        Component text = data.getText() instanceof PersonalTextElement ?
                ((PersonalTextElement) data.getText()).getRaw(p) :
                ((GlobalTextElement) data.getText()).getRaw();
        BossBar bossBar = bossBars.get(p);
        bossBar.name(text);
    }

    private BossBar constructBar(Player p) {
        Component text = data.getText() instanceof PersonalTextElement ?
                ((PersonalTextElement) data.getText()).getRaw(p) :
                ((GlobalTextElement) data.getText()).getRaw();

        return BossBar.bossBar(text, data.getProgress(), data.getColor(), data.getOverlay());
    }
}
