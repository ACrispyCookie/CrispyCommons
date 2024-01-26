package dev.acrispycookie.crispycommons.api.visuals.nametag;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.text.TextElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.AbstractAccessibleVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;
import org.bukkit.entity.Player;

import java.util.Set;

public class AbstractNametag extends AbstractAccessibleVisual<NameTagData> implements CrispyNametag {

    public AbstractNametag(NameTagData data, Set<? extends Player> receivers) {
        super(data, receivers);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void update() {

    }

    @Override
    public void setPrefix(TextElement prefix) {
        data.setPrefix(prefix);
    }

    @Override
    public void setSuffix(TextElement suffix) {
        data.setSuffix(suffix);
    }

    @Override
    public void setBelowName(TextElement belowName) {
        data.setBelowName(belowName);
    }

    @Override
    public void setAboveName(TextElement aboveName) {
        data.setAboveName(aboveName);
    }

    @Override
    public TextElement getPrefix() {
        return data.getPrefix();
    }

    @Override
    public TextElement getSuffix() {
        return data.getSuffix();
    }

    @Override
    public TextElement getBelowName() {
        return data.getBelowName();
    }

    @Override
    public TextElement getAboveName() {
        return data.getAboveName();
    }
}
