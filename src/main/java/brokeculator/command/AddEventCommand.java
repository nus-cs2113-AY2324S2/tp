package brokeculator.command;

import brokeculator.dashboard.Dashboard;
import brokeculator.event.Event;

public class AddEventCommand extends Command {
    
    private String eventName;
    private String eventDescription;

    public AddEventCommand(String eventName, String eventDescription) {
        assert eventName != null : "Event name cannot be null";
        assert eventDescription != null : "Event description cannot be null";
        this.eventName = eventName;
        this.eventDescription = eventDescription;
    }

    @Override
    public void execute(Dashboard dashboard) {
        Event event = new Event(eventName, eventDescription);
        dashboard.getEventManager().addEvent(event);
    }
}
