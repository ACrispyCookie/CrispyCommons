package dev.acrispycookie.crispycommons.holograms.lines;

import dev.acrispycookie.crispycommons.holograms.CrispyHologram;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHologramLine implements CrispyHologramLine {

    protected String line;
    protected Location location;
    protected ArrayList<Player> receivers = new ArrayList<>();

    public AbstractHologramLine(String initialLine) {
        this.line = initialLine;
    }

    @Override
    public String getCurrentText() {
        return line;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public void setReceivers(List<Player> receivers) {
        this.receivers.addAll(receivers);
    }
}
