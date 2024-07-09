package dev.acrispycookie.crispycommons.implementations.visuals.tablist;

import dev.acrispycookie.crispycommons.api.visuals.tablist.CrispyTablist;
import dev.acrispycookie.crispycommons.api.wrappers.elements.types.TextElement;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.wrappers.TablistData;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public abstract class AbstractTablist extends AbstractVisual<TablistData> implements CrispyTablist {

    protected abstract void show(Player p);
    protected abstract void hide(Player p);
    protected abstract void perPlayerUpdate(Player p);

    AbstractTablist(TablistData data, Set<? extends OfflinePlayer> receivers, long timeToLive, UpdateMode updateMode) {
        super(data, receivers, timeToLive, updateMode);
    }

    @Override
    protected void prepareShow() {
        data.getHeader().forEach(TextElement::start);
        data.getFooter().forEach(TextElement::start);
    }

    @Override
    protected void prepareHide() {
        data.getHeader().forEach(TextElement::stop);
        data.getFooter().forEach(TextElement::stop);
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
