package seedu.duke.parser;

import seedu.duke.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandMetadata {
    private final Pattern pattern;
    private final String[] groupArguments;
    private final Function<Map<String, String>, Command> constructor;

    CommandMetadata(
            Pattern pattern,
            String[] groupArguments,
            Function<Map<String, String>, Command> constructor
    ) {
        this.pattern = pattern;
        this.groupArguments = groupArguments;
        this.constructor = constructor;
    }

    Pattern getPattern() {
        return pattern;
    }

    String[] getGroupArguments() {
        return groupArguments;
    }

    Function<Map<String, String>, Command> getConstructor() {
        return constructor;
    }

    Map<String, String> getCommandArguments(Matcher matcher) {
        Map<String, String> arguments = new HashMap<>();

        for (String groupArgument : groupArguments) {
            String argument = matcher.group(groupArgument);
            if (groupArgument.equals("courseCode") || groupArgument.equals("grade")) {
                argument = argument.toUpperCase();
            }
            arguments.put(groupArgument, argument);
        }

        return arguments;
    }
}
