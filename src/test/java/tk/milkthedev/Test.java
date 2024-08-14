package tk.milkthedev;

import tk.milkthedev.consolemanager.api.Manager;
import tk.milkthedev.consolemanager.event.EventHandler;
import tk.milkthedev.consolemanager.event.EventPriority;
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

    @EventHandler(priority = EventPriority.NORMAL)
    public void onConsoleInputEventNormal(tk.milkthedev.consolemanager.event.impl.ConsoleInputEvent event) {
        System.out.println("Console input event NORMAL: " + event.getInput());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onConsoleInputEventLowest(tk.milkthedev.consolemanager.event.impl.ConsoleInputEvent event) {
        System.out.println("Console input event LOWEST: " + event.getInput());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onConsoleInputEventMonitor(tk.milkthedev.consolemanager.event.impl.ConsoleInputEvent event) {
        System.out.println("Console input event MONITOR: " + event.getInput());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onConsoleInputEventHighest(tk.milkthedev.consolemanager.event.impl.ConsoleInputEvent event) {
        System.out.println("Console input event HIGHEST: " + event.getInput());
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
