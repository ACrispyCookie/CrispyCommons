package dev.acrispycookie.crispycommons.implementations.visuals.nametag;

import dev.acrispycookie.crispycommons.api.visuals.nametag.CrispyNametag;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GlobalTextElement;
import org.bukkit.OfflinePlayer;

import java.util.Set;

public abstract class AbstractNametag extends AbstractVisual<NameTagData> implements CrispyNametag {

    AbstractNametag(NameTagData data, Set<? extends OfflinePlayer> receivers, long timeToLive, UpdateMode updateMode) {
        super(data, receivers, timeToLive, updateMode);
    }

    @Override
    protected void prepareShow() {
        data.getAboveName().start();
        data.getBelowName().start();
        data.getPrefix().start();
        data.getSuffix().start();
    }

    @Override
    protected void prepareHide() {
        data.getAboveName().stop();
        data.getBelowName().stop();
        data.getPrefix().stop();
        data.getSuffix().stop();
    }

    @Override
    public void setPrefix(GlobalTextElement prefix) {
        data.setPrefix(prefix);
    }

    @Override
    public void setSuffix(GlobalTextElement suffix) {
        data.setSuffix(suffix);
    }

    @Override
    public void setBelowName(GlobalTextElement belowName) {
        data.setBelowName(belowName);
    }

    @Override
    public void setAboveName(GlobalTextElement aboveName) {
        data.setAboveName(aboveName);
    }

    @Override
    public GlobalTextElement getPrefix() {
        return data.getPrefix();
    }

    @Override
    public GlobalTextElement getSuffix() {
        return data.getSuffix();
    }

    @Override
    public GlobalTextElement getBelowName() {
        return data.getBelowName();
    }

    @Override
    public GlobalTextElement getAboveName() {
        return data.getAboveName();
    }
}
