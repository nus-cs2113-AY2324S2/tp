package command;

public class ErrorCommand extends Command {

    @Override
    public void execute() {
        textBox.setNextError("Invalid Command here");
    }
}
