package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.RemoveCommand;

import java.util.Map;

public class RemoveCommandMetadata extends CommandMetadata {
    private static final String REMOVE_KEYWORD = "remove";
    private static final String[] REMOVE_ARGUMENTS = {"courseCode"};

    public RemoveCommandMetadata() {
        super(REMOVE_KEYWORD, REMOVE_ARGUMENTS);
    }

    // Remove Command Creator
    @Override
    protected Command createCommandInstance(Map<String, String> args) {
        return new RemoveCommand(args);
    }
}
