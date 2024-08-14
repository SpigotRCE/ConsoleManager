package tk.milkthedev.consolemanager.event;

import tk.milkthedev.consolemanager.event.listener.Listener;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class EventManager {
    private final Map<String, ArrayList<EventHandlerEntry>> eventHandlers;

    public EventManager() {
        this.eventHandlers = new HashMap<>();
    }

    public void registerEvent(Event event) {
        // Optionally: Ensure events are managed in some way if needed.
        System.out.println("Event registered: " + event.getEventName());
    }

    public void unregisterEvent(Event event) {
        // Optionally: Remove event from management if needed.
        System.out.println("Event unregistered: " + event.getEventName());
    }

    public void registerListener(Listener listener) {
        for (Method method : listener.getClass().getMethods()) {
            EventHandler handler = method.getAnnotation(EventHandler.class);
            if (handler != null) {
                if (!eventHandlers.containsKey(method.getParameterTypes()[0].getSimpleName())) {
                    eventHandlers.put(method.getParameterTypes()[0].getSimpleName(), new ArrayList<>());
                }
                eventHandlers.get(method.getParameterTypes()[0].getSimpleName()).add(new EventHandlerEntry(listener, method, handler));
                System.out.println("Listener registered: " + listener.getClass().getSimpleName() + " for method: " + method.getName());
            }
        }
    }

    public void unregisterListener(Listener listener) {
        eventHandlers.forEach((key, entries) -> entries.removeIf(entry -> entry.listener.equals(listener)));
        System.out.println("Listener unregistered: " + listener.getClass().getSimpleName());
    }

    public void fireEvent(Event event) {
        ArrayList<EventHandlerEntry> handlers = eventHandlers.get(event.getEventName());

        if (handlers == null) {
            return;
        }

        // Sort handlers by priority (LOWEST first)
        handlers.sort(Comparator.comparingInt(e -> e.handler.priority().getSlot()));

        for (EventHandlerEntry entry : handlers) {
            EventHandler handler = entry.handler;
            Method method = entry.method;
            Listener listener = entry.listener;

            // Check if the event is cancellable and if the handler should ignore cancelled events
            if (event instanceof Cancellable) {
                Cancellable cancellableEvent = (Cancellable) event;
                if (cancellableEvent.isCancel() && handler.ignoreCancelled()) {
                    continue; // Skip this handler if the event is cancelled and it should ignore cancelled events
                }
            }

            try {
                method.invoke(listener, event);
            } catch (Exception e) {
                e.printStackTrace(); // Print stack trace for debugging
            }
        }
    }

    private static class EventHandlerEntry {
        final Listener listener;
        final Method method;
        final EventHandler handler;

        EventHandlerEntry(Listener listener, Method method, EventHandler handler) {
            this.listener = listener;
            this.method = method;
            this.handler = handler;
        }
    }
}
