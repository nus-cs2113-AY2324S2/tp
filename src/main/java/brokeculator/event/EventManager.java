package brokeculator.event;

import java.util.ArrayList;

import brokeculator.frontend.UI;

public class EventManager {
    private static EventManager eventManager = null;
    private ArrayList<Event> events;

    private EventManager() {
        this.events = new ArrayList<>();
    }

    public static EventManager getInstance() {
        if (EventManager.eventManager == null) {
            EventManager.eventManager = new EventManager();
        }
        return EventManager.eventManager;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public void printEvents() {
        for (int idx = 0; idx < events.size(); idx++) {
            UI.println((idx + 1) + ". " + events.get(idx));
        }
    }
}
