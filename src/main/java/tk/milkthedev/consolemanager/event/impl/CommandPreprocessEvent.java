package tk.milkthedev.consolemanager.event.impl;

import tk.milkthedev.consolemanager.event.Cancellable;
import tk.milkthedev.consolemanager.event.Event;

/**
 * An event class that gets fired when a command is about to be executed
 */
public class CommandPreprocessEvent extends Event implements Cancellable {
    private final String command;
    private boolean cancel = false;

    /**
     * Constructs a new CommandPreprocessEvent with the given command
     *
     * @param command The command that is about to be executed
     */
    public CommandPreprocessEvent(String command) {
        this.command = command;
    }

    @Override
    public boolean isCancel() {
        return cancel;
    }

    @Override
    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Returns the command that is about to be executed
     *
     * @return The command
     */
    public String getCommand() {
        return command;
    }
}
