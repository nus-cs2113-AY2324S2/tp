package supertracker.command;

public class ExitCommand implements Command {
    @Override
    public void execute() {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
