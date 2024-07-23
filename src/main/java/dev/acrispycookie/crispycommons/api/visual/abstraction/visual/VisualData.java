package dev.acrispycookie.crispycommons.api.visual.abstraction.visual;

import org.bukkit.entity.Player;

public interface VisualData {

    void assertReady(Player p);

    class VisualNotReadyException extends RuntimeException {
        public VisualNotReadyException(String message) {
            super(message);
        }
    }
}
