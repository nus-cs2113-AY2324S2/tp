package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.RemoveCommand;

import java.util.Map;

public class RemoveCommandMetadata extends CommandMetadata {
    private static final String REMOVE_KEYWORD = "remove";
    private static final String REMOVE_PATTERN_REGEX = "remove\\s+c/(?<courseCode>[A-Za-z]{2,3}\\d{4}[A-Za-z]?)";
    private static final String[] REMOVE_ARGUMENTS = {"courseCode"};

    public RemoveCommandMetadata() {
        super(REMOVE_KEYWORD, REMOVE_PATTERN_REGEX, REMOVE_ARGUMENTS);
    }

    // Remove Command Creator
    @Override
    protected Command createCommandInstance(Map<String, String> args) {
        return new RemoveCommand(args);
    }
}
