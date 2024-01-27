package dev.acrispycookie.crispycommons.implementations.visuals.tablist;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractAccessibleVisual;
import dev.acrispycookie.crispycommons.api.visuals.tablist.CrispyTablist;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.wrappers.TablistData;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public abstract class AbstractTablist extends AbstractAccessibleVisual<TablistData> implements CrispyTablist {

    protected abstract void show(Player p);
    protected abstract void hide(Player p);
    protected abstract void update(Player p);

    AbstractTablist(TablistData data, Set<? extends Player> receivers) {
        super(data, receivers);
    }

    @Override
    public void show() {
        if(isDisplayed)
            return;

        isDisplayed = true;
        data.getHeader().forEach(TextElement::start);
        data.getFooter().forEach(TextElement::start);
        receivers.stream().filter(OfflinePlayer::isOnline).forEach(this::show);
    }

    @Override
    public void hide() {
        if(!isDisplayed)
            return;

        isDisplayed = false;
        data.getHeader().forEach(TextElement::stop);
        data.getFooter().forEach(TextElement::stop);
        receivers.stream().filter(OfflinePlayer::isOnline).forEach(this::hide);
    }

    @Override
    public void update() {
        if(!isDisplayed)
            return;

        receivers.stream().filter(OfflinePlayer::isOnline).forEach(this::update);
    }

    @Override
    public void setHeader(List<TextElement> elements) {
        this.data.setHeader(elements);
    }

    @Override
    public void setFooter(List<TextElement> elements) {
        this.data.setFooter(elements);
    }

    public List<TextElement> getHeader() {
        return this.data.getHeader();
    }

    public List<TextElement> getFooter() {
        return this.data.getFooter();
    }
}
