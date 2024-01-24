package dev.acrispycookie.crispycommons.implementations.visuals.message;

import dev.acrispycookie.crispycommons.utility.message.MessageType;
import dev.acrispycookie.crispycommons.utility.visual.AbstractCrispyAccessibleVisual;
import org.bukkit.entity.Player;

import java.util.Set;

public class AbstractCrispyMessage extends AbstractCrispyAccessibleVisual<String> implements CrispyMessage {

    private final MessageType type;

    public AbstractCrispyMessage(MessageType type, String text, Set<? extends Player> receivers) {
        super(text, receivers);
        this.type = type;
    }

    @Override
    public void setText(String text) {

    }

    @Override
    public void setFadeIn(int fadeIn) {

    }

    @Override
    public void setDuration(int duration) {

    }

    @Override
    public void setFadeOut(int fadeOut) {

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
}
