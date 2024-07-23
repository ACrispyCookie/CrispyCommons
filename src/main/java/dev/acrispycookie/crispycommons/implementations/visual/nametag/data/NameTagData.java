package dev.acrispycookie.crispycommons.implementations.visual.nametag.data;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.element.type.NameTagElement;
import org.bukkit.entity.Player;

public class NameTagData implements VisualData {

    private Player player;
    private NameTagElement<String, ?> prefix;
    private NameTagElement<String, ?> suffix;
    private NameTagElement<Integer, ?> belowNameValue;
    private NameTagElement<String, ?> belowName;
    private NameTagElement<String, ?> aboveName;

    public NameTagData(Player player, NameTagElement<String, ?> prefix, NameTagElement<String, ?> suffix, NameTagElement<Integer, ?> belowNameValue, NameTagElement<String, ?> belowName, NameTagElement<String, ?> aboveName) {
        this.player = player;
        this.prefix = prefix;
        this.suffix = suffix;
        this.belowName = belowName;
        this.belowNameValue = belowNameValue;
        this.aboveName = aboveName;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public NameTagElement<String, ?> getPrefix() {
        return prefix;
    }

    public void setPrefix(NameTagElement<String, ?> prefix) {
        this.prefix = prefix;
    }

    public NameTagElement<String, ?> getSuffix() {
        return suffix;
    }

    public void setSuffix(NameTagElement<String, ?> suffix) {
        this.suffix = suffix;
    }

    public NameTagElement<String, ?> getBelowName() {
        return belowName;
    }

    public void setBelowName(NameTagElement<String, ?> belowName) {
        this.belowName = belowName;
    }

    public NameTagElement<Integer, ?> getBelowNameValue() {
        return belowNameValue;
    }

    public void setBelowNameValue(NameTagElement<Integer, ?> belowNameValue) {
        this.belowNameValue = belowNameValue;
    }

    public NameTagElement<String, ?> getAboveName() {
        return aboveName;
    }

    public void setAboveName(NameTagElement<String, ?> aboveName) {
        this.aboveName = aboveName;
    }

    @Override
    public void assertReady(Player player) {
        if (this.player == null)
            throw new VisualNotReadyException("The name tag's player was not set!");
        if (prefix == null)
            throw new VisualNotReadyException("The name tag's prefix was not set!");
        if (suffix == null)
            throw new VisualNotReadyException("The name tag's suffix was not set!");
        if (belowNameValue != null && belowName == null)
            throw new VisualNotReadyException("The below name value was set while below name display wasn't set!");
        if (belowName != null && belowNameValue == null)
            throw new VisualNotReadyException("The below name display was set while below name value wasn't set!");
    }
}
