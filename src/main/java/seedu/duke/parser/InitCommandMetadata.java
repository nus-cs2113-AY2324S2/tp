package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.InitCommand;

import java.util.Map;

public class InitCommandMetadata extends CommandMetadata {
    private static final String INIT_KEYWORD = "init";
    private static final String[] INIT_ARGUMENTS = {"name"};

    public InitCommandMetadata() {
        super(INIT_KEYWORD, INIT_ARGUMENTS);
    }

    // Init Command Creator
    @Override
    protected Command createCommandInstance(Map<String, String> args) {
        String name = args.getOrDefault("name", "NAME_ERROR");

        return new InitCommand(name);
    }
}
