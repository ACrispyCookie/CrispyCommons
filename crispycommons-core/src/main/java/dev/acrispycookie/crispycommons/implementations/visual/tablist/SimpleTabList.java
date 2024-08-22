package dev.acrispycookie.crispycommons.implementations.visual.tablist;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.implementations.visual.tablist.data.TabListData;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import net.kyori.adventure.Adventure;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

/**
 * A concrete implementation of {@link AbstractTabList} that manages and displays
 * a simple tab list with header and footer lines to players.
 * <p>
 * {@code SimpleTabList} sends the tab list header and footer to each player individually,
 * and updates the display as necessary. This implementation updates players on a per-player basis.
 * </p>
 */
public class SimpleTabList extends AbstractTabList {

    /**
     * Constructs a {@code SimpleTabList} with the specified data, receivers, time-to-live, and visibility.
     *
     * @param data the {@link TabListData} containing the header and footer lines.
     * @param receivers the set of players who will receive the tab list updates.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the tab list.
     * @param isPublic whether the tab list should be visible to all players.
     */
    public SimpleTabList(TabListData data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, boolean isPublic) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER, isPublic);
    }

    /**
     * Displays the tab list header and footer to the specified player.
     * <p>
     * This method constructs the header and footer components and sends them to the player using
     * the {@link Adventure} API.
     * </p>
     *
     * @param p the player to whom the tab list will be shown.
     */
    @Override
    protected void show(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        Component header = constructComponent(getHeader(), p);
        Component footer = constructComponent(getFooter(), p);
        audience.sendPlayerListHeaderAndFooter(header, footer);
    }

    /**
     * Hides the tab list from the specified player.
     * <p>
     * This method clears the header and footer for the player by sending empty components.
     * </p>
     *
     * @param p the player from whom the tab list will be hidden.
     */
    @Override
    protected void hide(Player p) {
        Audience audience = CrispyCommons.getBukkitAudiences().player(p);
        Component header = Component.empty();
        Component footer = Component.empty();
        audience.sendPlayerListHeaderAndFooter(header, footer);
    }

    /**
     * Updates the tab list for a specific player.
     * <p>
     * This method re-shows the tab list to the player, effectively updating its content.
     * </p>
     *
     * @param p the player for whom the tab list will be updated.
     */
    @Override
    protected void perPlayerUpdate(Player p) {
        show(p);
    }

    /**
     * Updates the tab list globally.
     * <p>
     * This method is empty because global updates are not used in this implementation.
     * </p>
     */
    @Override
    protected void globalUpdate() {
        // No global update action needed.
    }

    /**
     * Adds a line to the header or footer and triggers an update if necessary.
     * <p>
     * This method is called internally when a line is added to the header or footer.
     * </p>
     *
     * @param index the index where the line was added.
     * @param header {@code true} if the line was added to the header, {@code false} if it was added to the footer.
     */
    @Override
    protected void addLineInternal(int index, boolean header) {
        if (isAnyoneWatching()) {
            update();
        }
    }

    /**
     * Removes a line from the header or footer and triggers an update if necessary.
     * <p>
     * This method is called internally when a line is removed from the header or footer.
     * </p>
     *
     * @param index the index of the line that was removed.
     * @param header {@code true} if the line was removed from the header, {@code false} if it was removed from the footer.
     */
    @Override
    protected void removeLineInternal(int index, boolean header) {
        if (isAnyoneWatching()) {
            update();
        }
    }

    /**
     * Updates the header or footer lines and triggers a global update if necessary.
     * <p>
     * This method is called internally to update all lines in the header or footer.
     * </p>
     *
     * @param header {@code true} if updating the header, {@code false} if updating the footer.
     */
    @Override
    protected void updateLines(boolean header) {
        if (isAnyoneWatching()) {
            update();
        }
    }

    /**
     * Constructs a {@link Component} from a list of {@link TextElement} for a specific player.
     * <p>
     * The component is constructed by appending each element's content and adding a newline
     * between them.
     * </p>
     *
     * @param elements the list of elements to construct the component from.
     * @param player the player for whom the component is being constructed.
     * @return the constructed {@link Component}.
     */
    private Component constructComponent(List<TextElement<?>> elements, OfflinePlayer player) {
        Component component = Component.empty();
        for (int i = 0; i < elements.size(); i++) {
            Component toAdd = elements.get(i).getFromContext(OfflinePlayer.class, player);
            if (toAdd == null) {
                continue;
            }
            component = component.append(toAdd);
            if (i != elements.size() - 1) {
                component = component.appendNewline();
            }
        }

        return component;
    }
}

