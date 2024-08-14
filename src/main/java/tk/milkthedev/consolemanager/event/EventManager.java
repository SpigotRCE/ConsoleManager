package tk.milkthedev.consolemanager.event;

import tk.milkthedev.consolemanager.event.listener.Listener;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class EventManager {
    private final ArrayList<Event> events;
    private final ArrayList<Listener> listeners;
    private final ArrayList<EventHandler> handlers;
    private final ArrayList<Method> handlerMethods;

    public EventManager() {
        this.events = new ArrayList<>();
        this.listeners = new ArrayList<>();
        this.handlers = new ArrayList<>();
        this.handlerMethods = new ArrayList<>();
    }

    public void registerEvent(Event event) {
        events.add(event);
    }

    public void unregisterEvent(Event event) {
        events.remove(event);
    }

    public void registerListener(Listener listener) {
        for (Method method : listener.getClass().getMethods()) {
            EventHandler handler = method.getAnnotation(EventHandler.class);
            if (handler != null) {
                handlers.add(handler);
                handlerMethods.add(method);
                listeners.add(listener);
            }
        }
    }

    public void unregisterListener(Listener listener) {
        listeners.remove(listener);
    }

    public void fireEvent(Event event) {
        ArrayList<Event> events1 = new ArrayList<>();
        ArrayList<EventHandler> handlers1 = new ArrayList<>();

        events.forEach(e -> {
            if (e.getEventName().equals(event.getEventName())) {
                events1.add(e);
                handlers1.add(handlers.get(events.indexOf(e)));
            }
        });

        // TODO: Sort on the basis of priority
        for (Method method : handlerMethods) {
            try {
                method.invoke(listeners.get(handlerMethods.indexOf(method)), event);
            } catch (Exception ignored) { // TODO: to only invoke methods which have the same event as argument
            }
        }
    }
}
