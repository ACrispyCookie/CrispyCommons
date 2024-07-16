package dev.acrispycookie.crispycommons.api.guis.abstraction;

import org.bukkit.OfflinePlayer;

public interface TrackedGui extends CrispyGui {
    boolean isPlayerViewing(OfflinePlayer player);
}
