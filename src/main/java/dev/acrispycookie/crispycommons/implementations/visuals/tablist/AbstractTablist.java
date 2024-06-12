package dev.acrispycookie.crispycommons.implementations.visuals.tablist;

import dev.acrispycookie.crispycommons.api.visuals.tablist.CrispyTablist;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.wrappers.TablistData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GlobalTextElement;
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
        data.getHeader().forEach(GlobalTextElement::start);
        data.getFooter().forEach(GlobalTextElement::start);
    }

    @Override
    protected void prepareHide() {
        data.getHeader().forEach(GlobalTextElement::stop);
        data.getFooter().forEach(GlobalTextElement::stop);
    }

    @Override
    public void setHeader(List<GlobalTextElement> elements) {
        this.data.setHeader(elements);
    }

    @Override
    public void setFooter(List<GlobalTextElement> elements) {
        this.data.setFooter(elements);
    }

    public List<GlobalTextElement> getHeader() {
        return this.data.getHeader();
    }

    public List<GlobalTextElement> getFooter() {
        return this.data.getFooter();
    }
}
