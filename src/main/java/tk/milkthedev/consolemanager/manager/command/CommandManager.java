package tk.milkthedev.consolemanager.manager.command;

import tk.milkthedev.consolemanager.api.Manager;
import tk.milkthedev.consolemanager.event.impl.ConsoleInputEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager {
    private final ArrayList<Command> commands;
    private final Manager manager;

    public CommandManager(Manager manager) {
        this.commands = new ArrayList<>();
        this.manager = manager;
    }

    public void registerCommand(Command command) {
        commands.add(command);
    }

    public void unregisterCommand(Command command) {
        commands.remove(command);
    }

    public boolean executeCommand(String input) {
        String[] parts = input.split(" ");
        String commandName = parts[0];
        Command command = getCommand(commandName);

        if (command == null) return false;

        ConsoleInputEvent event = new ConsoleInputEvent(input);
        manager.getEventManager().fireEvent(event);

        if (event.isCancel()) return true;

        command.execute(Arrays.copyOfRange(parts, 1, parts.length));
        return true;
    }

    public Command getCommand(String name) {
        for (Command command : commands) {
            if (command.getName().equalsIgnoreCase(name)) {
                return command;
            }
        }
        return null;
    }
}
