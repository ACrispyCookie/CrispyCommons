package dev.acrispycookie.crispycommons.implementations.visuals.bossbar;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.visuals.bossbar.wrappers.BossbarData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalGeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.type.GlobalTextElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types.PersonalGeneralElement;
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

    public SimpleBossbar(BossbarData data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long> timeToLive) {
        super(data, receivers, timeToLive);
    }

    @Override
    protected void show(Player p) {
        bossBars.put(p, BossBar.bossBar(getText(p), getProgress(p), getColor(p), getOverlay(p)));
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
        BossBar bossBar = bossBars.get(p);
        bossBar.name(getText(p));
        bossBar.color(getColor(p));
        bossBar.progress(getProgress(p));
        bossBar.overlay(getOverlay(p));
    }

    private Component getText(Player p) {
        return data.getText() instanceof PersonalTextElement ?
                ((PersonalTextElement) data.getText()).getRaw(p) :
                ((GlobalTextElement) data.getText()).getRaw();
    }

    private float getProgress(Player p) {
        return data.getProgress() instanceof PersonalGeneralElement ?
                ((PersonalGeneralElement<Float>) data.getProgress()).getRaw(p) :
                ((GlobalGeneralElement<Float>) data.getProgress()).getRaw();
    }

    private BossBar.Color getColor(Player p) {
        return data.getColor() instanceof PersonalGeneralElement ?
                ((PersonalGeneralElement<BossBar.Color>) data.getColor()).getRaw(p) :
                ((GlobalGeneralElement<BossBar.Color>) data.getColor()).getRaw();
    }

    private BossBar.Overlay getOverlay(Player p) {
        return data.getOverlay() instanceof PersonalGeneralElement ?
                ((PersonalGeneralElement<BossBar.Overlay>) data.getOverlay()).getRaw(p) :
                ((GlobalGeneralElement<BossBar.Overlay>) data.getOverlay()).getRaw();
    }
}
