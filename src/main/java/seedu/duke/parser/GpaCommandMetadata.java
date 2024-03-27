package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.ViewGpaCommand;

import java.util.Map;

public class GpaCommandMetadata extends CommandMetadata {
    private static final String GPA_KEYWORD = "gpa";
    private static final String[] GPA_ARGUMENTS = {};

    public GpaCommandMetadata() {
        super(GPA_KEYWORD, GPA_ARGUMENTS);
    }

    // Gpa Command Creator
    @Override
    protected Command createCommandInstance(Map<String, String> args) {
        return new ViewGpaCommand();
    }
}
