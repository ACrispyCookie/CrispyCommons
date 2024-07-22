package dev.acrispycookie.crispycommons.implementations.visuals.hologram;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.visuals.hologram.CrispyHologram;
import dev.acrispycookie.crispycommons.api.wrappers.elements.DynamicElement;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.hologram.wrappers.HologramData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TimeToLiveElement;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public abstract class AbstractHologram extends AbstractVisual<HologramData> implements CrispyHologram {

    protected abstract void updateLines();
    protected abstract void removeLineInternal(int index);
    protected abstract void addLineInternal(int index);

    AbstractHologram(HologramData data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, UpdateMode updateMode, boolean isPublic) {
        super(data, receivers, timeToLive, updateMode, isPublic);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (getPlayers().contains(event.getEntity()) && isDisplayed)
            hide(event.getEntity());
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (getPlayers().contains(event.getPlayer()) && isDisplayed) {
            Bukkit.getScheduler().runTaskLater(CrispyCommons.getPlugin(), () -> show(event.getPlayer()), 1L);
        }
    }

    @Override
    protected void prepareShow() {
        data.getLines().forEach(DynamicElement::start);
        data.getLocation().start();
    }

    @Override
    protected void prepareHide() {
        data.getLines().forEach(DynamicElement::stop);
        data.getLocation().stop();
    }


    @Override
    public void addLine(int index, DynamicElement<?, ?> line) {
        if (index > data.getLines().size())
            return;

        line.setUpdate(this::update);
        if (isAnyoneWatching())
            line.start();
        data.addLine(index, line);
        addLineInternal(index);
    }

    @Override
    public void addLine(DynamicElement<?, ?> line) {
        addLine(data.getLines().size(), line);
    }

    @Override
    public void removeLine(int index) {
        if(index >= data.getLines().size())
            return;

        if (isAnyoneWatching())
            data.getLines().get(index).stop();
        data.removeLine(index);
        removeLineInternal(index);
    }

    @Override
    public void setLines(Collection<? extends DynamicElement<?, ?>> lines) {
        lines.forEach((l) -> l.setUpdate(this::update));
        if (isAnyoneWatching()) {
            data.getLines().forEach(DynamicElement::stop);
            lines.forEach(DynamicElement::start);
        }
        data.setLines(new ArrayList<>(lines));
        updateLines();
    }

    @Override
    public void setLocation(GeneralElement<Location, ?> location) {
        data.setLocation(location);
    }

    @Override
    public GeneralElement<Location, ?> getLocation() {
        return data.getLocation();
    }


    @Override
    public List<DynamicElement<?, ?>> getLines() {
        return data.getLines();
    }
}
