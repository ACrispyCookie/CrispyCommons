package dev.acrispycookie.crispycommons.api.visuals.tablist;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.wrappers.TablistData;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public abstract class AbstractTablist extends AbstractAccessibleVisual<TablistData> implements CrispyTablist {

    protected abstract void show(Player p);
    protected abstract void hide(Player p);
    protected abstract void update(Player p);

    public AbstractTablist(TablistData data, Set<? extends Player> receivers) {
        super(data, receivers);
    }

    @Override
    public void show() {
        if(isDisplayed)
            return;

        this.isDisplayed = true;
        receivers.stream().filter(OfflinePlayer::isOnline).forEach(this::show);
        data.getHeader().forEach(TextElement::start);
        data.getFooter().forEach(TextElement::start);
    }

    @Override
    public void hide() {
        if(!isDisplayed)
            return;

        this.isDisplayed = false;
        receivers.stream().filter(OfflinePlayer::isOnline).forEach(this::hide);
        data.getHeader().forEach(TextElement::stop);
        data.getFooter().forEach(TextElement::stop);
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
