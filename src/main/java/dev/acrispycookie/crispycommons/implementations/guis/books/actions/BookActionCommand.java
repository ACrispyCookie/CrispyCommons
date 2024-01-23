package dev.acrispycookie.crispycommons.implementations.guis.books.actions;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BookActionCommand extends BukkitCommand {

    private static BookActionCommand instance;

    public BookActionCommand(String name) {
        super(name);
        instance = this;
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length != 1 && !(commandSender instanceof Player))
            return false;

        Player p = (Player) commandSender;
        UUID uuid = UUID.fromString(strings[0]);
        BookAction action = BookAction.getPendingAction(uuid);
        if(action != null) {
            action.performAction(p);
        }
        return true;
    }

    public static BookActionCommand getInstance() {
        return instance;
    }
}
