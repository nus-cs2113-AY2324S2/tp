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
    public Event getEvent(int idx) {
        if (!isEventIdxValid(idx)) {
            return null;
        }
        return events.get(idx - 1);
    }
    public void removeEvent(int idx) {
        if (!isEventIdxValid(idx)) {
            return;
        }
        events.remove(idx - 1);
    }

    public void printEvents() {
        if (events.isEmpty()) {
            UI.prettyPrint("No events found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < events.size(); idx++) {
            sb.append(idx + 1).append(". ").append(events.get(idx));
            sb.append(System.lineSeparator());
        }
        UI.prettyPrint(sb.toString());
    }
}


