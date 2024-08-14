package tk.milkthedev;

import tk.milkthedev.consolemanager.api.Manager;
import tk.milkthedev.consolemanager.event.EventHandler;
import tk.milkthedev.consolemanager.event.listener.Listener;
import tk.milkthedev.consolemanager.manager.command.Command;

import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class Test implements Listener {
    private final Manager manager;

    public Test() {
        this.manager = new Manager();
        this.manager.getCommandManager().registerCommand(new TestCommand());
        this.manager.getEventManager().registerListener(this);
        new Thread(this.manager.getConsoleHandler()).start();
    }

    public static void main(String[] args) {
        Test test = new Test();
    }

    @EventHandler
    public void onCommandPreprocess(tk.milkthedev.consolemanager.event.impl.CommandPreprocessEvent event) {
        System.out.println("Command preprocess event: " + event.getCommand());
    }

    @EventHandler
    public void onConsoleInputEvent(tk.milkthedev.consolemanager.event.impl.ConsoleInputEvent event) {
        System.out.println("Console input event: " + event.getInput());
    }

    public static class TestCommand extends Command {
        public TestCommand() {
            super("test", "Test command");
        }

        @Override
        public void execute(String[] args) {
            System.out.println(Arrays.toString(args));
        }
    }
}
