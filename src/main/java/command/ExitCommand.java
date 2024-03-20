package command;

public class ExitCommand extends Command{

    protected static boolean isExit = false;

    public ExitCommand(boolean isExit) {
        ExitCommand.isExit = isExit;
    }

    public static boolean getIsExit() {
        return isExit;
    }

    @Override
    public void execute() {

    }
}
