package brokeculator.command;
import brokeculator.dashboard.Dashboard;
import brokeculator.frontend.UI;

public class ExitCommand extends Command {
    public static final String GOODBYE_STRING = "Goodbye!";
    public void execute(Dashboard dashboard) {
        UI.print(GOODBYE_STRING);
        System.exit(0);
    }
}
