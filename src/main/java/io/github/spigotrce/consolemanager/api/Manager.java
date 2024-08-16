package io.github.spigotrce.consolemanager.api;

import io.github.spigotrce.consolemanager.event.EventManager;
import io.github.spigotrce.consolemanager.handler.ConsoleHandler;
import io.github.spigotrce.consolemanager.manager.command.CommandManager;

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
