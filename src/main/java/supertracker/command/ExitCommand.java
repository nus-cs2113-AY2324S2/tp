package supertracker.command;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\tExiting...");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
