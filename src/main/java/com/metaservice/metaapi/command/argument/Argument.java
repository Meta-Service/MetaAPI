package com.metaservice.metaapi.command.argument;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Represents a command argument that can be associated with a {@link BaseCommand}.
 */
@Getter
@AllArgsConstructor
public class Argument {

    /**
     * The name of the argument.
     */
    private final String name;

    /**
     * The list of aliases for the argument.
     */
    private final List<String> aliases;

    /**
     * The description of the argument.
     */
    private final String description;

    /**
     * The executor responsible for handling the logic of the argument.
     */
    private final ArgumentExecutor executor;

    /**
     * Checks if the provided argument matches the name or any alias of this argument.
     *
     * @param arg The argument to check.
     * @return True if the argument matches the name or any alias; otherwise, false.
     */
    public boolean matches(String arg) {
        return name.equalsIgnoreCase(arg) || aliases.contains(arg.toLowerCase());
    }

    /**
     * Executes the logic associated with this argument.
     *
     * @param sender The CommandSender who executed the command.
     * @param args   The command arguments.
     */
    public void execute(CommandSender sender, String[] args) {
        executor.execute(sender, args);
    }
}
