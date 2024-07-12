package dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.NameTagElement;
import org.bukkit.entity.Player;

public class NameTagData implements VisualData {

    private GeneralElement<Player, ?> player;
    private NameTagElement<?> prefix;
    private NameTagElement<?> suffix;
    private NameTagElement<?> belowName;
    private NameTagElement<?> aboveName;

    public NameTagData(GeneralElement<Player, ?> player, NameTagElement<?> prefix, NameTagElement<?> suffix, NameTagElement<?> belowName, NameTagElement<?> aboveName) {
        this.player = player;
        this.prefix = prefix;
        this.suffix = suffix;
        this.belowName = belowName;
        this.aboveName = aboveName;
    }

    public GeneralElement<Player, ?> getPlayer() {
        return player;
    }

    public void setPlayer(GeneralElement<Player, ?> player) {
        this.player = player;
    }

    public NameTagElement<?> getPrefix() {
        return prefix;
    }

    public void setPrefix(NameTagElement<?> prefix) {
        this.prefix = prefix;
    }

    public NameTagElement<?> getSuffix() {
        return suffix;
    }

    public void setSuffix(NameTagElement<?> suffix) {
        this.suffix = suffix;
    }

    public NameTagElement<?> getBelowName() {
        return belowName;
    }

    public void setBelowName(NameTagElement<?> belowName) {
        this.belowName = belowName;
    }

    public NameTagElement<?> getAboveName() {
        return aboveName;
    }

    public void setAboveName(NameTagElement<?> aboveName) {
        this.aboveName = aboveName;
    }

    @Override
    public void assertReady(Player player) {
        if (player == null)
            throw new VisualNotReadyException("The name tag player was not set!");
        if (prefix == null)
            throw new VisualNotReadyException("The name tag prefix was not set!");
        if (suffix == null)
            throw new VisualNotReadyException("The name tag suffix was not set!");
        if (belowName == null)
            throw new VisualNotReadyException("The name tag below name element was not set!");
        if (aboveName == null)
            throw new VisualNotReadyException("The name tag above name element was not set!");
    }
}
