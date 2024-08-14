package tk.milkthedev.consolemanager.api;

import tk.milkthedev.consolemanager.event.EventManager;
import tk.milkthedev.consolemanager.handler.ConsoleHandler;
import tk.milkthedev.consolemanager.manager.command.CommandManager;

public class Manager {
    private final CommandManager commandManager;
    private final ConsoleHandler consoleHandler;
    private final EventManager eventManager;

    public Manager() {
        this.commandManager = new CommandManager(this);
        this.consoleHandler = new ConsoleHandler(this);
        this.eventManager = new EventManager();
    }

    public Manager(CommandManager commandManager, ConsoleHandler consoleHandler, EventManager eventManager) {
        this.commandManager = commandManager;
        this.consoleHandler = consoleHandler;
        this.eventManager = eventManager;
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    public ConsoleHandler getConsoleHandler() {
        return this.consoleHandler;
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }
}
