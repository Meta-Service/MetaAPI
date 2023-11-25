package com.metaservice.metaapi.command;

import com.metaservice.metaapi.MetaAPI;
import com.metaservice.metaapi.command.argument.Argument;
import com.metaservice.metaapi.util.Color;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

/**
 * The base class for custom commands in your plugin.
 * Extend this class to create new commands with specific functionality.
 */
@Getter
@Setter
public abstract class BaseCommand {

    /**
     * The name of the command.
     */
    private final String name;

    /**
     * The permission required to execute the command.
     */
    private String permission = null;

    /**
     * The list of usage lines for the command.
     */
    private List<String> usage = null;

    /**
     * The list of aliases for the command.
     */
    private List<String> aliases = null;

    /**
     * The list of arguments associated with the command.
     */
    private List<Argument> arguments = Collections.emptyList();

    /**
     * Flag indicating whether the command is intended for players only.
     */
    private Boolean playerOnly = false;

    /**
     * Creates a new instance of BaseCommand with the specified name.
     *
     * @param name The name of the command.
     */
    public BaseCommand(String name) {
        this.name = name;
    }

    /**
     * Adds an argument to the command.
     *
     * @param argument The argument to add.
     */
    public void addArgument(Argument argument) {
        arguments.add(argument);
    }

    /**
     * Executes the command logic.
     *
     * @param sender The CommandSender who executed the command.
     * @param args   The command arguments.
     */
    public abstract void execute(CommandSender sender, String[] args);

    /**
     * Converts the BaseCommand to a Bukkit Command for registration.
     *
     * @return The Bukkit Command representing this BaseCommand.
     */
    public Command toBukkitCommand() {
        return new Command(name) {
            @Override
            public boolean execute(CommandSender sender, String s, String[] args) {
                if (playerOnly && !(sender instanceof Player)) {
                    sender.sendMessage(Color.translate("&cThis command is only for players."));
                    return false;
                }

                if (permission != null && !sender.hasPermission(permission)) {
                    sender.sendMessage(Color.translate("&cYou don't have permission for this command."));
                    return false;
                }
                executeCommand(sender, args);
                return true;
            }
        };
    }

    public void register() {
        MetaAPI.getInstance().getVersionManager().getVersion().getCommandMap().register(getName(), toBukkitCommand());
    }

    /**
     * Executes the command based on the provided arguments.
     *
     * @param sender The CommandSender who executed the command.
     * @param args   The command arguments.
     */
    private void executeCommand(CommandSender sender, String[] args) {
        if (args.length == 0) {
            execute(sender, args);
            return;
        }

        if (!arguments.isEmpty()) {
            for (Argument argument : arguments) {
                if (argument.matches(args[0])) {
                    argument.execute(sender, args);
                    return;
                }
            }
            sendUsage(sender);
            return;
        }

        execute(sender, args);
    }

    /**
     * Sends the command usage to the specified CommandSender.
     *
     * @param sender The CommandSender to receive the usage information.
     */
    public void sendUsage(CommandSender sender) {
        usage.forEach(sender::sendMessage);
    }
}