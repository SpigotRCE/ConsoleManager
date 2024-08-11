package tk.milkthedev.consolemanager.event.impl;

import tk.milkthedev.consolemanager.event.Cancellable;
import tk.milkthedev.consolemanager.event.Event;

public class CommandPreprocessEvent extends Event implements Cancellable {
    private boolean cancel = false;
    private final String command;

    public CommandPreprocessEvent( String command) {
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

    public String getCommand() {
        return command;
    }
}
