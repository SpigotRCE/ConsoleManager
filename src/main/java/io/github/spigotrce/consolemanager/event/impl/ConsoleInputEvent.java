package io.github.spigotrce.consolemanager.event.impl;

import io.github.spigotrce.consolemanager.event.Event;
import io.github.spigotrce.consolemanager.event.Cancellable;

/**
 * An event class that gets fired when an input is made on the console
 */
public class ConsoleInputEvent extends Event implements Cancellable {
    private boolean cancel = false;
    private String input;

    /**
     * Constructs a new ConsoleInputEvent with the given input
     *
     * @param input The input made on the console
     */
    public ConsoleInputEvent(String input) {
        this.input = input;
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
     * Gets the input made on the console
     *
     * @return The input
     */
    public String getInput() {
        return input;
    }

    /**
     * Sets the input made on the console
     *
     * @param input The new input
     */
    public void setInput(String input) {
        this.input = input;
    }
}
