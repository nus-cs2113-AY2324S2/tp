package supertracker.command;

public class ListCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\tSorry! Invalid command");
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
