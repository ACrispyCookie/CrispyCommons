package dev.acrispycookie.crispycommons.implementations.visuals.tablist;

import dev.acrispycookie.crispycommons.api.visuals.tablist.CrispyTabList;
import dev.acrispycookie.crispycommons.api.wrappers.elements.DynamicElement;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.tablist.wrappers.TabListData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.AbstractDynamicElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TextElement;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public abstract class AbstractTabList extends AbstractVisual<TabListData> implements CrispyTabList {

    protected abstract void updateLines(boolean header);
    protected abstract void removeLineInternal(int index, boolean header);
    protected abstract void addLineInternal(int index, boolean header);

    AbstractTabList(TabListData data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long, ?> timeToLive, UpdateMode updateMode) {
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
    public void addHeaderLine(int index, TextElement<?> line) {
        if (index > data.getHeader().size())
            return;

        line.setUpdate(this::update);
        if (isAnyoneWatching())
            line.start();
        data.addHeaderLine(index, line);
        addLineInternal(index, true);
    }

    @Override
    public void addHeaderLine(TextElement<?> line) {
        addHeaderLine(data.getHeader().size(), line);
    }

    @Override
    public void removeHeaderLine(int index) {
        if(index >= data.getHeader().size())
            return;

        if (isAnyoneWatching())
            data.getHeader().get(index).stop();
        data.removeHeaderLine(index);
        removeLineInternal(index, true);
    }

    @Override
    public void setHeader(Collection<? extends TextElement<?>> lines) {
        lines.forEach((l) -> l.setUpdate(this::update));
        if (isAnyoneWatching()) {
            data.getHeader().forEach(DynamicElement::stop);
            lines.forEach(AbstractDynamicElement::start);
        }
        data.setHeader(new ArrayList<>(lines));
        updateLines(true);
    }

    @Override
    public void addFooterLine(int index, TextElement<?> line) {
        if (index > data.getFooter().size())
            return;

        line.setUpdate(this::update);
        if (isAnyoneWatching())
            line.start();
        data.addFooterLine(index, line);
        addLineInternal(index, false);
    }

    @Override
    public void addFooterLine(TextElement<?> line) {
        addFooterLine(data.getFooter().size(), line);
    }

    @Override
    public void removeFooterLine(int index) {
        if(index >= data.getFooter().size())
            return;

        if (isAnyoneWatching())
            data.getFooter().get(index).stop();
        data.removeFooterLine(index);
        removeLineInternal(index, false);
    }

    @Override
    public void setFooter(Collection<? extends TextElement<?>> lines) {
        lines.forEach((l) -> l.setUpdate(this::update));
        if (isAnyoneWatching()) {
            data.getFooter().forEach(DynamicElement::stop);
            lines.forEach(AbstractDynamicElement::start);
        }
        data.setFooter(new ArrayList<>(lines));
        updateLines(true);
    }

    public List<TextElement<?>> getHeader() {
        return this.data.getHeader();
    }

    public List<TextElement<?>> getFooter() {
        return this.data.getFooter();
    }
}
