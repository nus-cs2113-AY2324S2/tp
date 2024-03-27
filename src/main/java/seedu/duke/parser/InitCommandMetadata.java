package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.InitCommand;

import java.util.Map;

public class InitCommandMetadata extends CommandMetadata {
    private static final String INIT_KEYWORD = "init";
    private static final String INIT_PATTERN_REGEX = "init\\s+n/(?<name>[A-Za-z0-9 ]+)";
    private static final String[] INIT_ARGUMENTS = {"name"};

    public InitCommandMetadata() {
        super(INIT_KEYWORD, INIT_PATTERN_REGEX, INIT_ARGUMENTS);
    }

    // Init Command Creator
    @Override
    protected Command createCommandInstance(Map<String, String> args) {
        String name = args.getOrDefault("name", "NAME_ERROR");

        return new InitCommand(name);
    }
}
