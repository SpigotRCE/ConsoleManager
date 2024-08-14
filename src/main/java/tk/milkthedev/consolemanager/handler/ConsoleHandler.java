package tk.milkthedev.consolemanager.handler;

import tk.milkthedev.consolemanager.api.Manager;
import tk.milkthedev.consolemanager.event.impl.ConsoleInputEvent;
import tk.milkthedev.consolemanager.manager.command.CommandManager;

import java.util.Objects;
import java.util.Scanner;


/**
 * This class handles the console input
 */
public class ConsoleHandler implements Runnable {
    private final Manager manager;
    private final CommandManager commandManager;

    /**
     * Constructs a new ConsoleHandler with the given Manager
     *
     * @param manager the Manager to use
     */
    public ConsoleHandler(Manager manager) {
        this.manager = manager;
        this.commandManager = manager.getCommandManager();
    }

    /**
     * Runs the console handler in a separate thread
     */
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (!Thread.interrupted()) {
            String input = scanner.nextLine();
            if (!Objects.equals(input.trim(), "")) {
                ConsoleInputEvent event = new ConsoleInputEvent(input);
                manager.getEventManager().fireEvent(event);
                if (event.isCancel()) continue;
                commandManager.executeCommand(input.trim());
            }
        }
    }
}
