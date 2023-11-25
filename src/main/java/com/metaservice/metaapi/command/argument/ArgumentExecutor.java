package com.metaservice.metaapi.command.argument;

import org.bukkit.command.CommandSender;

/**
 * A functional interface representing the executor for handling logic associated with a command argument.
 */
@FunctionalInterface
public interface ArgumentExecutor {

    /**
     * Executes the logic associated with a command argument.
     *
     * @param sender The CommandSender who executed the command.
     * @param args   The command arguments.
     */
    void execute(CommandSender sender, String[] args);
}
