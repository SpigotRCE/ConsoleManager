package io.github.spigotrce.consolemanager.manager.command;

/**
 * Represents a Command
 */
public abstract class Command {
    private final String name;
    private final String description;
    /**
     * Constructs a new Command with the given name and description
     *
     * @param name       The name of the command
     * @param description The description of the command
     */
    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Executes the command with the provided arguments
     *
     * @param args The arguments passed to the command
     */
    public abstract void execute(String[] args);
}
