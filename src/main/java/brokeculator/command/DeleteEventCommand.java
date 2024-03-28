package brokeculator.command;

import brokeculator.dashboard.Dashboard;
import brokeculator.event.Event;
import brokeculator.frontend.UI;

public class DeleteEventCommand extends Command {
    private final int idx;

    public DeleteEventCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(Dashboard dashboard) {
        boolean isValidEventIdx = dashboard.getEventManager().isEventIdxValid(idx);
        if (!isValidEventIdx) {
            UI.prettyPrint("Invalid event index");
            return;
        }
        Event event = dashboard.getEventManager().getEvent(idx);
        if (event.hasExpenses()) {
            UI.prettyPrint("Event has expenses. Please remove expenses before deleting event");
            return;
        }
        dashboard.getEventManager().removeEvent(idx);
        UI.prettyPrint("Event deleted");
    }
}
