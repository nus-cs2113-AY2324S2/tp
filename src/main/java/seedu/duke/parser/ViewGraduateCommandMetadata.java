package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.ViewGraduateCommand;

import java.util.Map;

public class ViewGraduateCommandMetadata extends CommandMetadata {
    private static final String GRADUATE_KEYWORD = "graduate";
    private static final String[] GRADUATE_ARGUMENTS = {};

    public ViewGraduateCommandMetadata() {
        super(GRADUATE_KEYWORD, GRADUATE_ARGUMENTS);
    }

    // View Graduate Command Creator
    @Override
    protected Command createCommandInstance(Map<String, String> args) {
        return new ViewGraduateCommand();
    }
}
