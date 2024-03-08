package supertracker.command;

public class InvalidCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\tSorry! Invalid command");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
