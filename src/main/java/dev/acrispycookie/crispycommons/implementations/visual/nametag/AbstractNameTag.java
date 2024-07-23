package dev.acrispycookie.crispycommons.implementations.visual.nametag;

import dev.acrispycookie.crispycommons.api.visual.nametag.CrispyNameTag;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visual.nametag.data.NameTagData;
import dev.acrispycookie.crispycommons.implementations.element.type.NameTagElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import org.bukkit.OfflinePlayer;

import java.util.Set;

public abstract class AbstractNameTag extends AbstractVisual<NameTagData> implements CrispyNameTag {

    AbstractNameTag(NameTagData data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, UpdateMode updateMode, boolean isPublic) {
        super(data, receivers, timeToLive, updateMode, isPublic);
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
        if (isAnyoneWatching()) {
            data.getPrefix().start();
            update();
        }
    }

    @Override
    public void setSuffix(NameTagElement<String, ?> suffix) {
        data.getSuffix().stop();
        data.setSuffix(suffix);
        data.getSuffix().setUpdate(this::update);
        if (isAnyoneWatching()) {
            data.getSuffix().start();
            update();
        }
    }

    @Override
    public void setBelowName(NameTagElement<String, ?> belowName) {
        data.getBelowName().stop();
        data.setBelowName(belowName);
        data.getBelowName().setUpdate(this::update);
        if (isAnyoneWatching()) {
            data.getBelowName().start();
            update();
        }
    }

    @Override
    public void setBelowNameValue(NameTagElement<Integer, ?> belowNameValue) {
        data.getBelowNameValue().stop();
        data.setBelowNameValue(belowNameValue);
        data.getBelowNameValue().setUpdate(this::update);
        if (isAnyoneWatching()) {
            data.getBelowNameValue().start();
            update();
        }
    }

    @Override
    public void setAboveName(NameTagElement<String, ?> aboveName) {
        data.getAboveName().stop();
        data.setAboveName(aboveName);
        data.getAboveName().setUpdate(this::update);
        if (isAnyoneWatching()) {
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
