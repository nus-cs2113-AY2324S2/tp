package brokeculator.command;

import brokeculator.dashboard.Dashboard;

public abstract class Command {
    public Command() {};
    public abstract void execute(Dashboard dashboard);
}
