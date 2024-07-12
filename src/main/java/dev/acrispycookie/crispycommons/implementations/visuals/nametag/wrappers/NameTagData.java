package dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.TextElement;
import org.bukkit.entity.Player;

public class NameTagData implements VisualData {

    private GeneralElement<Player, ?> player;
    private TextElement<?> prefix;
    private TextElement<?> suffix;
    private TextElement<?> belowName;
    private TextElement<?> aboveName;

    public NameTagData(GeneralElement<Player, ?> player, TextElement<?> prefix, TextElement<?> suffix, TextElement<?> belowName, TextElement<?> aboveName) {
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

    public TextElement<?> getPrefix() {
        return prefix;
    }

    public void setPrefix(TextElement<?> prefix) {
        this.prefix = prefix;
    }

    public TextElement<?> getSuffix() {
        return suffix;
    }

    public void setSuffix(TextElement<?> suffix) {
        this.suffix = suffix;
    }

    public TextElement<?> getBelowName() {
        return belowName;
    }

    public void setBelowName(TextElement<?> belowName) {
        this.belowName = belowName;
    }

    public TextElement<?> getAboveName() {
        return aboveName;
    }

    public void setAboveName(TextElement<?> aboveName) {
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
