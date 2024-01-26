package dev.acrispycookie.crispycommons.api.visuals.nametag;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Set;

public abstract class AbstractNametag extends AbstractAccessibleVisual<NameTagData> implements CrispyNametag {

    protected abstract void showPlayer(Player p);
    protected abstract void hidePlayer(Player p);
    protected abstract void updatePlayer(Player p);

    public AbstractNametag(NameTagData data, Set<? extends Player> receivers) {
        super(data, receivers);
    }

    @Override
    public void show() {
        if (isDisplayed)
            return;

        isDisplayed = true;
        data.getAboveName().start();
        data.getBelowName().start();
        data.getPrefix().start();
        data.getSuffix().start();
        receivers.stream().filter(OfflinePlayer::isOnline).forEach(this::showPlayer);
    }

    @Override
    public void hide() {
        if (!isDisplayed)
            return;

        isDisplayed = false;
        data.getAboveName().stop();
        data.getBelowName().stop();
        data.getPrefix().stop();
        data.getSuffix().stop();
        receivers.stream().filter(OfflinePlayer::isOnline).forEach(this::hidePlayer);
    }

    @Override
    public void update() {
        if (!isDisplayed)
            return;

        receivers.stream().filter(OfflinePlayer::isOnline).forEach(this::updatePlayer);
    }

    @Override
    public void setPrefix(TextElement prefix) {
        data.setPrefix(prefix);
    }

    @Override
    public void setSuffix(TextElement suffix) {
        data.setSuffix(suffix);
    }

    @Override
    public void setBelowName(TextElement belowName) {
        data.setBelowName(belowName);
    }

    @Override
    public void setAboveName(TextElement aboveName) {
        data.setAboveName(aboveName);
    }

    @Override
    public TextElement getPrefix() {
        return data.getPrefix();
    }

    @Override
    public TextElement getSuffix() {
        return data.getSuffix();
    }

    @Override
    public TextElement getBelowName() {
        return data.getBelowName();
    }

    @Override
    public TextElement getAboveName() {
        return data.getAboveName();
    }
}
