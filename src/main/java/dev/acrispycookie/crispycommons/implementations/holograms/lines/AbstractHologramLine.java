package dev.acrispycookie.crispycommons.implementations.holograms.lines;

import dev.acrispycookie.crispycommons.utility.elements.CrispyElement;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class AbstractHologramLine<T extends CrispyElement<K>, K> implements HologramLine<K> {

    protected T element;
    protected boolean isDisplayed;
    protected Location location;
    protected final Set<Player> receivers = new HashSet<>();
    protected abstract void display(Player player);
    protected abstract void destroy(Player player);
    protected abstract void update(Player player);

    public AbstractHologramLine(T element, List<Player> receivers) {
        this.element = element;
        this.receivers.addAll(receivers);
    }

    @Override
    public K getCurrentElement() {
        return element.getElement();
    }

    @Override
    public void display() {
        if (isDisplayed) {
            return;
        }

        receivers.forEach(this::display);
        isDisplayed = true;
    }

    @Override
    public void destroy() {
        if (!isDisplayed) {
            return;
        }

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
    public void setPlayers(Player... players) {
        receivers.clear();
        receivers.addAll(Arrays.asList(players));
        if (isDisplayed) {
            destroy();
            display();
        }
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
        if (isDisplayed) {
            destroy();
            display();
        }
    }

    @Override
    public boolean isDisplayed() {
        return isDisplayed;
    }

}
