package dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TextElement;
import org.bukkit.entity.Player;

public class NameTagData implements VisualData {

    private Player player;
    private TextElement prefix;
    private TextElement suffix;
    private TextElement belowName;
    private TextElement aboveName;
    private boolean hologramName;
    private boolean hologramBelowName;

    public NameTagData(Player player, TextElement prefix, TextElement suffix, TextElement belowName, TextElement aboveName, boolean hologramName, boolean hologramBelowName) {
        this.player = player;
        this.prefix = prefix;
        this.suffix = suffix;
        this.belowName = belowName;
        this.aboveName = aboveName;
        this.hologramName = hologramName;
        this.hologramBelowName = hologramBelowName;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public TextElement getPrefix() {
        return prefix;
    }

    public void setPrefix(TextElement prefix) {
        this.prefix = prefix;
    }

    public TextElement getSuffix() {
        return suffix;
    }

    public void setSuffix(TextElement suffix) {
        this.suffix = suffix;
    }

    public TextElement getBelowName() {
        return belowName;
    }

    public void setBelowName(TextElement belowName) {
        this.belowName = belowName;
    }

    public TextElement getAboveName() {
        return aboveName;
    }

    public void setAboveName(TextElement aboveName) {
        this.aboveName = aboveName;
    }

    public boolean isHologramName() {
        return hologramName;
    }

    public boolean isHologramBelowName() {
        return hologramBelowName;
    }

    public void setNameMode(boolean hologramName) {
        this.hologramName = hologramName;
    }

    public void setBelowNameMode(boolean hologramBelowName) {
        this.hologramBelowName = hologramBelowName;
    }

    @Override
    public void assertReady() {
        if (player == null)
            throw new VisualNotReadyException("The nametag player was not set!");
        if (prefix == null)
            throw new VisualNotReadyException("The nametag prefix was not set!");
        if (suffix == null)
            throw new VisualNotReadyException("The nametag suffix was not set!");
        if (belowName == null)
            throw new VisualNotReadyException("The nametag below name element was not set!");
        if (aboveName == null)
            throw new VisualNotReadyException("The nametag above name element was not set!");
    }
}
