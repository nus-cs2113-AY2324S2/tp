package brokeculator.command;
import brokeculator.UI;

public class ExitCommand extends Command {
    public static final String GOODBYE_STRING = "Goodbye!";
    public void execute() {
        UI.print(GOODBYE_STRING);
        System.exit(0);
    }
}
