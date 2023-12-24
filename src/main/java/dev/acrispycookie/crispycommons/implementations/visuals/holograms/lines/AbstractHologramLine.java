package dev.acrispycookie.crispycommons.implementations.visuals.holograms.lines;

import dev.acrispycookie.crispycommons.implementations.visuals.holograms.CrispyHologram;
import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;
import dev.acrispycookie.crispycommons.utility.showable.AbstractCrispyShowable;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class AbstractHologramLine<T extends AnimatedElement<K>, K> extends AbstractCrispyShowable implements HologramLine<K> {

    protected T element;
    protected CrispyHologram hologram;
    protected final Set<Player> receivers = new HashSet<>();
    protected abstract void display(Player player);
    protected abstract void destroy(Player player);
    protected abstract void update(Player player);

    public AbstractHologramLine(T element, Collection<? extends Player> receivers) {
        super(receivers);
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
        receivers.forEach(this::display);
        isDisplayed = true;
    }

    @Override
    public void hide() {
        if (!isDisplayed) {
            return;
        }

        element.stop();
        receivers.forEach(this::destroy);
        isDisplayed = false;
    }

    @Override
    public void update() {
        if (isDisplayed) {
            receivers.forEach(this::update);
        }
    }

    @Override
    public void addPlayer(Player player) {
        receivers.add(player);
        if (isDisplayed) {
            display(player);
        }
    }

    @Override
    public void removePlayer(Player player) {
        receivers.remove(player);
        if (isDisplayed) {
            destroy(player);
        }
    }

    @Override
    public void setPlayers(Collection<? extends Player> players) {
        receivers.clear();
        receivers.addAll(players);
        if (isDisplayed) {
            hide();
            show();
        }
    }

    @Override
    public boolean isDisplayed() {
        return isDisplayed;
    }

    @Override
    public List<Player> getPlayers() {
        return new ArrayList<>(receivers);
    }

    @Override
    public K getCurrentContent() {
        return element.getContent();
    }

    @Override
    public void setHologram(CrispyHologram hologram) {
        this.hologram = hologram;
    }

    @Override
    public void updateLocation() {
        if (isDisplayed) {
            hide();
            show();
        }
    }

}
