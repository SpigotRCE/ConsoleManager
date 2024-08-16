package io.github.spigotrce.consolemanager.manager.command;

import io.github.spigotrce.consolemanager.api.Manager;
import io.github.spigotrce.consolemanager.event.impl.CommandPreprocessEvent;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Manages all registered commands.
 */
public class CommandManager {
    private final ArrayList<Command> commands;
    private final Manager manager;

    /**
     * Constructs a new command manager.
     *
     * @param manager The manager instance.
     */
    public CommandManager(Manager manager) {
        this.commands = new ArrayList<>();
        this.manager = manager;
    }

    /**
     * Registers the command
     * @param command The command to register
     */
    public void registerCommand(Command command) {
        commands.add(command);
    }

    /**
     * Unregisters the command
     * @param command The command to unregister
     */
    public void unregisterCommand(Command command) {
        commands.remove(command);
    }

    /**
     * Executes the given command.
     * @param input The command to execute
     * @return True if the command was executed successfully, false otherwise
     */
    public boolean executeCommand(String input) {
        String[] parts = input.split(" ");
        String commandName = parts[0];
        Command command = getCommand(commandName);

        if (command == null) return false;

        CommandPreprocessEvent event = new CommandPreprocessEvent(input);
        manager.getEventManager().fireEvent(event);

        if (event.isCancel()) return true;

        command.execute(Arrays.copyOfRange(parts, 1, parts.length));
        return true;
    }

    /**
     * Gets the command by its name
     * @param name The name of the command
     * @return The command with the given name, or null if not found
     */
    public Command getCommand(String name) {
        for (Command command : commands) {
            if (command.getName().equalsIgnoreCase(name)) {
                return command;
            }
        }
        return null;
    }
}
