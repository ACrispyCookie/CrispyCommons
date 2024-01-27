package dev.acrispycookie.crispycommons.implementations.visuals.actionbar;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.api.visuals.actionbar.CrispyActionbar;
import dev.acrispycookie.crispycommons.implementations.visuals.actionbar.wrappers.ActionbarData;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public abstract class AbstractActionbar extends AbstractVisual<ActionbarData> implements CrispyActionbar {

    protected abstract void showPlayer(Player p);

    AbstractActionbar(ActionbarData data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive);
    }

    @Override
    public void onShow() {
        data.getText().start();
        receivers.stream().filter(OfflinePlayer::isOnline).forEach(p -> showPlayer(p.getPlayer()));
    }

    @Override
    public void onHide() {
        data.getText().stop();
    }

    @Override
    public void onUpdate() {
        receivers.stream().filter(OfflinePlayer::isOnline).forEach(p -> showPlayer(p.getPlayer()));
    }

    @Override
    public void setText(TextElement text) {
        data.setText(text);
    }

    @Override
    public TextElement getText() {
        return data.getText();
    }
}
