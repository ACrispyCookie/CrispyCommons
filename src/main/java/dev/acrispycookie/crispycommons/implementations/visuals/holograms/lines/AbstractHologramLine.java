package dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines;

import dev.acrispycookie.crispycommons.implementations.visuals.holograms.CrispyHologram;
import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;
import dev.acrispycookie.crispycommons.utility.showable.AbstractCrispyShowable;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class AbstractHologramLine<T extends AnimatedElement<K>, K> extends AbstractCrispyShowable<K> implements HologramLine<K> {

    protected T element;
    protected CrispyHologram hologram;
    protected abstract void show(Player player);
    protected abstract void hide(Player player);
    protected abstract void update(Player player);

    public AbstractHologramLine(T element, Collection<? extends Player> receivers) {
        super(new HashSet<>(receivers));
        this.element = element;
        this.hologram = null;
        this.receivers.addAll(receivers);
    }

    @Override
    public void show() {
        if (isDisplayed || hologram == null) {
            return;
        }

        element.start();
        receivers.forEach(this::show);
        isDisplayed = true;
        hologram.update();
    }

    @Override
    public void hide() {
        if (!isDisplayed) {
            return;
        }

        element.stop();
        receivers.forEach(this::hide);
        isDisplayed = false;
        hologram.update();
    }

    @Override
    public void update() {
        if (isDisplayed) {
            receivers.forEach(this::update);
        }
    }

    @Override
    public void addPlayer(Player player) {
        super.addPlayer(player);
        if (isDisplayed) {
            show(player);
        }
    }

    @Override
    public void removePlayer(Player player) {
        super.removePlayer(player);
        if (isDisplayed) {
            hide(player);
        }
    }

    @Override
    public void setPlayers(Collection<? extends Player> players) {
        super.setPlayers(players);
        if (isDisplayed) {
            hide();
            show();
        }
    }

    @Override
    public K getCurrentContent() {
        return element.getContent();
    }

    @Override
    public void setHologram(CrispyHologram hologram) {
        this.hologram = hologram;
    }

}
