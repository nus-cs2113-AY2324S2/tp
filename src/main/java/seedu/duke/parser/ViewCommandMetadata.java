package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.ViewCommand;

import java.util.Map;

public class ViewCommandMetadata extends CommandMetadata {
    private static final String VIEW_KEYWORD = "view";
    private static final String VIEW_PATTERN_REGEX = "view";
    private static final String[] VIEW_ARGUMENTS = {};

    public ViewCommandMetadata() {
        super(VIEW_KEYWORD, VIEW_PATTERN_REGEX, VIEW_ARGUMENTS);
    }

    // View Command Creator
    @Override
    protected Command createCommandInstance(Map<String, String> args) {
        return new ViewCommand();
    }
}
