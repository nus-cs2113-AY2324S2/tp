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

    public boolean isEventIdxValid(int idx) {
        return idx >= 1 && idx <= events.size();
    }

    public void removeEvent(int idx) {
        assert isEventIdxValid(idx);
        events.remove(idx - 1);
    }

    public void printEvents() {
        for (int idx = 0; idx < events.size(); idx++) {
            UI.println((idx + 1) + ". " + events.get(idx));
        }
    }
}
