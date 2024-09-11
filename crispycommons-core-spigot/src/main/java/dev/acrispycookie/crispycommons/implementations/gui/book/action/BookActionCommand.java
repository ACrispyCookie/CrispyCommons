package dev.acrispycookie.crispycommons.implementations.gui.book.action;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * A command class that handles the execution of {@link BookAction} instances.
 * <p>
 * The {@code BookActionCommand} is used to trigger actions associated with specific UUIDs
 * passed as arguments to the command. This class ensures that the action is performed by
 * the appropriate player and manages the lifecycle of these actions.
 * </p>
 */
public class BookActionCommand extends BukkitCommand {

    /**
     * The singleton instance of this command.
     */
    private static BookActionCommand instance;

    /**
     * Constructs a {@code BookActionCommand} with the specified command name.
     * <p>
     * This constructor also sets the singleton instance to the newly created command.
     * </p>
     *
     * @param name the name of the command.
     */
    public BookActionCommand(@NotNull String name) {
        super(name);
        instance = this;
    }

    /**
     * Executes the command to trigger a {@link BookAction}.
     * <p>
     * This method attempts to retrieve a {@link BookAction} by UUID, passed as the first argument,
     * and if found, performs the action for the player who issued the command.
     * </p>
     *
     * @param commandSender the sender of the command, expected to be a {@link Player}.
     * @param s             the command label.
     * @param strings       the command arguments, where the first argument should be a UUID string.
     * @return {@code true} if the command was executed successfully, otherwise {@code true} (this implementation does not return false).
     */
    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, String[] strings) {
        if (strings.length != 1 || !(commandSender instanceof Player))
            return true;

        Player p = (Player) commandSender;
        try {
            UUID uuid = UUID.fromString(strings[0]);
            BookAction action = BookAction.getPendingAction(uuid);
            if (action != null) {
                action.performAction(p);
            }
            return true;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    /**
     * Returns the singleton instance of {@code BookActionCommand}.
     *
     * @return the instance of this command.
     */
    public static @NotNull BookActionCommand getInstance() {
        return instance;
    }
}

