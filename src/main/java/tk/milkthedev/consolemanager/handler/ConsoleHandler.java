package tk.milkthedev.consolemanager.handler;

import tk.milkthedev.consolemanager.api.Manager;
import tk.milkthedev.consolemanager.event.impl.ConsoleInputEvent;
import tk.milkthedev.consolemanager.manager.command.CommandManager;

import java.util.Objects;
import java.util.Scanner;

public class ConsoleHandler implements Runnable {
    private final Manager manager;
    private final CommandManager commandManager;

    public ConsoleHandler(Manager manager) {
        this.manager = manager;
        this.commandManager = manager.getCommandManager();
    }

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
