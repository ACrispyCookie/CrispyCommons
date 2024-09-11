package dev.acrispycookie.crispycommons.platform.commands;

import dev.acrispycookie.crispycommons.platform.commands.PlatformCommand;
import org.bukkit.command.Command;

public abstract class SpigotCommand implements PlatformCommand {

    private final Command command;

    public SpigotCommand(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
