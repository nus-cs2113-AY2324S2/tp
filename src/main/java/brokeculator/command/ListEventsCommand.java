package brokeculator.command;

import brokeculator.dashboard.Dashboard;

public class ListEventsCommand extends Command {
    @Override
    public void execute(Dashboard dashboard) {
        dashboard.getEventManager().printEvents();
    }
}
