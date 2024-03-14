package seedu.brokeculator;

public class ExitCommand extends Command {
    public void execute() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
