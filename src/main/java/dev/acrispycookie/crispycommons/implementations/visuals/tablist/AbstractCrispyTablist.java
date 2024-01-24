package dev.acrispycookie.crispycommons.implementations.visuals.tablist;

import dev.acrispycookie.crispycommons.utility.elements.DynamicElement;
import dev.acrispycookie.crispycommons.utility.elements.implementations.text.StringElement;
import dev.acrispycookie.crispycommons.utility.visual.AbstractCrispyAccessibleVisual;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public abstract class AbstractCrispyTablist extends AbstractCrispyAccessibleVisual<List<List<StringElement>>> implements CrispyTablist {

    protected abstract void show(Player p);
    protected abstract void hide(Player p);
    protected abstract void update(Player p);

    public AbstractCrispyTablist(List<List<StringElement>> content, Set<? extends Player> receivers) {
        super(content, receivers);
    }

    @Override
    public void show() {
        if(isDisplayed)
            return;

        this.isDisplayed = true;
        receivers.forEach(this::show);
        content.forEach(l -> l.forEach(DynamicElement::start));
    }

    @Override
    public void hide() {
        if(!isDisplayed)
            return;

        this.isDisplayed = false;
        receivers.forEach(this::hide);
        content.forEach(l -> l.forEach(DynamicElement::stop));
    }

    @Override
    public void update() {
        if(!isDisplayed)
            return;

        receivers.forEach(this::update);
    }

    @Override
    public void setHeader(List<StringElement> element) {
        this.content.set(0, element);
    }

    @Override
    public void setFooter(List<StringElement> element) {
        this.content.set(1, element);
    }

    public List<StringElement> getHeader() {
        return this.content.get(0);
    }

    public List<StringElement> getFooter() {
        return this.content.get(1);
    }
}
