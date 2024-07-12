package dev.acrispycookie.crispycommons.implementations.visuals.nametag;

import dev.acrispycookie.crispycommons.api.visuals.nametag.CrispyNameTag;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.nametag.wrappers.NameTagData;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.NameTagElement;
import org.bukkit.OfflinePlayer;

import java.util.Set;

public abstract class AbstractNameTag extends AbstractVisual<NameTagData> implements CrispyNameTag {

    AbstractNameTag(NameTagData data, Set<? extends OfflinePlayer> receivers, GeneralElement<Long, ?> timeToLive, UpdateMode updateMode) {
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
    public void setPrefix(NameTagElement<?> prefix) {
        data.getPrefix().stop();
        data.setPrefix(prefix);
        data.getPrefix().setUpdate(this::update);
        if (isDisplayed) {
            data.getPrefix().start();
            update();
        }
    }

    @Override
    public void setSuffix(NameTagElement<?> suffix) {
        data.getSuffix().stop();
        data.setSuffix(suffix);
        data.getSuffix().setUpdate(this::update);
        if (isDisplayed) {
            data.getSuffix().start();
            update();
        }
    }

    @Override
    public void setBelowName(NameTagElement<?> belowName) {
        data.getBelowName().stop();
        data.setBelowName(belowName);
        data.getBelowName().setUpdate(this::update);
        if (isDisplayed) {
            data.getBelowName().start();
            update();
        }
    }

    @Override
    public void setAboveName(NameTagElement<?> aboveName) {
        data.getAboveName().stop();
        data.setAboveName(aboveName);
        data.getAboveName().setUpdate(this::update);
        if (isDisplayed) {
            data.getAboveName().start();
            update();
        }
    }

    @Override
    public NameTagElement<?> getPrefix() {
        return data.getPrefix();
    }

    @Override
    public NameTagElement<?> getSuffix() {
        return data.getSuffix();
    }

    @Override
    public NameTagElement<?> getBelowName() {
        return data.getBelowName();
    }

    @Override
    public NameTagElement<?> getAboveName() {
        return data.getAboveName();
    }
}
