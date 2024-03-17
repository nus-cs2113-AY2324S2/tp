package seedu.duke.command;

public class InvalidCommand extends Command {
    @Override
    public void execute(String userInput) {
        System.out.println("INVALID COMMAND");
    }
}
