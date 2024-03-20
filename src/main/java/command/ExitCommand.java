package command;

public class ExitCommand extends Command{

    protected boolean isExit = false;

    public ExitCommand(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean getIsExit() {
        return isExit;
    }

    @Override
    public void execute() {

    }
}
