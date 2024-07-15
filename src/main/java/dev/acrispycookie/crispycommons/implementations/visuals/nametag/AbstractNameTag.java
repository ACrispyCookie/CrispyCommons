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
        if (data.getAboveName() != null)
            data.getAboveName().start();
        if (data.getBelowName() != null)
            data.getBelowName().start();
        if (data.getBelowNameValue() != null)
            data.getBelowNameValue().start();
        if (data.getPrefix() != null)
            data.getPrefix().start();
        if (data.getSuffix() != null)
            data.getSuffix().start();
    }

    @Override
    protected void prepareHide() {
        if (data.getAboveName() != null)
            data.getAboveName().stop();
        if (data.getBelowName() != null)
            data.getBelowName().stop();
        if (data.getBelowNameValue() != null)
            data.getBelowNameValue().stop();
        if (data.getPrefix() != null)
            data.getPrefix().stop();
        if (data.getSuffix() != null)
            data.getSuffix().stop();
    }

    @Override
    public void setPrefix(NameTagElement<String, ?> prefix) {
        data.getPrefix().stop();
        data.setPrefix(prefix);
        data.getPrefix().setUpdate(this::update);
        if (isDisplayed) {
            data.getPrefix().start();
            update();
        }
    }

    @Override
    public void setSuffix(NameTagElement<String, ?> suffix) {
        data.getSuffix().stop();
        data.setSuffix(suffix);
        data.getSuffix().setUpdate(this::update);
        if (isDisplayed) {
            data.getSuffix().start();
            update();
        }
    }

    @Override
    public void setBelowName(NameTagElement<String, ?> belowName) {
        data.getBelowName().stop();
        data.setBelowName(belowName);
        data.getBelowName().setUpdate(this::update);
        if (isDisplayed) {
            data.getBelowName().start();
            update();
        }
    }

    @Override
    public void setBelowNameValue(NameTagElement<Integer, ?> belowNameValue) {
        data.getBelowNameValue().stop();
        data.setBelowNameValue(belowNameValue);
        data.getBelowNameValue().setUpdate(this::update);
        if (isDisplayed) {
            data.getBelowNameValue().start();
            update();
        }
    }

    @Override
    public void setAboveName(NameTagElement<String, ?> aboveName) {
        data.getAboveName().stop();
        data.setAboveName(aboveName);
        data.getAboveName().setUpdate(this::update);
        if (isDisplayed) {
            data.getAboveName().start();
            update();
        }
    }

    @Override
    public NameTagElement<String, ?> getPrefix() {
        return data.getPrefix();
    }

    @Override
    public NameTagElement<String, ?> getSuffix() {
        return data.getSuffix();
    }

    @Override
    public NameTagElement<String, ?> getBelowName() {
        return data.getBelowName();
    }

    @Override
    public NameTagElement<Integer, ?> getBelowNameValue() {
        return data.getBelowNameValue();
    }

    @Override
    public NameTagElement<String, ?> getAboveName() {
        return data.getAboveName();
    }
}
