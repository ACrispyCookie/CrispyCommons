package dev.acrispycookie.crispycommons.api.visuals.tablist;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.DynamicElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractCrispyAccessibleVisual;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public abstract class AbstractCrispyTablist extends AbstractCrispyAccessibleVisual<List<List<TextElement>>> implements CrispyTablist {

    protected abstract void show(Player p);
    protected abstract void hide(Player p);
    protected abstract void update(Player p);

    public AbstractCrispyTablist(List<List<TextElement>> content, Set<? extends Player> receivers) {
        super(content, receivers);
    }

    @Override
    public void show() {
        if(isDisplayed)
            return;

        this.isDisplayed = true;
        receivers.stream().filter(OfflinePlayer::isOnline).forEach(this::show);
        content.forEach(l -> l.forEach(DynamicElement::start));
    }

    @Override
    public void hide() {
        if(!isDisplayed)
            return;

        this.isDisplayed = false;
        receivers.stream().filter(OfflinePlayer::isOnline).forEach(this::hide);
        content.forEach(l -> l.forEach(DynamicElement::stop));
    }

    @Override
    public void update() {
        if(!isDisplayed)
            return;

        receivers.stream().filter(OfflinePlayer::isOnline).forEach(this::update);
    }

    @Override
    public void setHeader(List<TextElement> element) {
        this.content.set(0, element);
    }

    @Override
    public void setFooter(List<TextElement> element) {
        this.content.set(1, element);
    }

    public List<TextElement> getHeader() {
        return this.content.get(0);
    }

    public List<TextElement> getFooter() {
        return this.content.get(1);
    }
}
