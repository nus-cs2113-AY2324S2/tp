package brokeculator.command;

import brokeculator.dashboard.Dashboard;
import brokeculator.frontend.UI;

public class InvalidCommand extends Command {
    private String errorMessage;
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(Dashboard dashboard) {
        UI.print(errorMessage);
    }
}
