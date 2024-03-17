package seedu.duke.command;

import seedu.duke.modules.Module;

public class InvalidCommand extends Command {
    @Override
    public void execute(String userInput) {
        System.out.println("INVALID COMMAND");
    }
}
