package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.ByeCommand;

import java.util.Map;

public class ByeCommandMetadata extends CommandMetadata {
    private static final String BYE_KEYWORD = "bye";
    private static final String BYE_PATTERN_REGEX = "bye";
    private static final String[] BYE_ARGUMENTS = {};

    public ByeCommandMetadata() {
        super(BYE_KEYWORD, BYE_PATTERN_REGEX, BYE_ARGUMENTS);
    }

    // Bye Command Creator
    @Override
    protected Command createCommandInstance(Map<String, String> args) {
        return new ByeCommand();
    }
}
