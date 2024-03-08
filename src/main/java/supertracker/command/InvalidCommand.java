package supertracker.command;

public class InvalidCommand implements Command {
    @Override
    public void execute() {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
