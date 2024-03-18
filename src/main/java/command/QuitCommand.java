package command;

public class QuitCommand extends Command{
    @Override
    public void execute() {
        System.exit(0);
    }
}
