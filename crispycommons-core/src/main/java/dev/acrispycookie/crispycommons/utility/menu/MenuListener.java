package dev.acrispycookie.crispycommons.utility.menu;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.version.VersionManager;
import dev.acrispycookie.crispycommons.version.Versioned;
import dev.acrispycookie.crispycommons.version.utility.MappedVersions;
import dev.acrispycookie.crispycommons.version.utility.Version;
import dev.acrispycookie.crispycommons.version.utility.VersionPair;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * A listener class for handling various inventory and player-related events in the {@link CrispyMenu} system.
 * <p>
 * This class listens for events related to inventory interactions, including clicking, closing, block breaking
 * near open menus, and player damage while interacting with open menus. It ensures that CrispyMenu's behavior,
 * such as item interactions and menu protection, is correctly managed according to the menu's configuration and
 * properties.
 * </p>
 */
public abstract class MenuListener implements Listener, Versioned {

    @EventHandler
    protected abstract void onClick(InventoryClickEvent event);

    @EventHandler
    protected abstract void onClose(InventoryCloseEvent event);

    @EventHandler
    protected abstract void onBlockBreak(BlockBreakEvent event);

    @EventHandler
    protected abstract void onPlayerDamage(EntityDamageEvent event);

    public static MenuListener newInstance() {
        return VersionManager.createInstance(MenuListener.class, getRemapped());
    }

    public static MappedVersions getRemapped() {
        return new MappedVersions(new VersionPair(Version.v1_20_R3, Version.v1_8_R3));
    }
}
