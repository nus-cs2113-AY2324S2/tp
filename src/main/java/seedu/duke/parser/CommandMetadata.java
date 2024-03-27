package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.exceptions.ParserException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CommandMetadata {

    private static Logger logger = Logger.getLogger("CommandMetadata");
    private static Map<String, String> argRegexMap = new HashMap<>();
    static {
        logger.setLevel(Level.OFF);

        argRegexMap.put("name", "n/(?<name>[A-Za-z0-9 ]+)");
        argRegexMap.put("courseCode", "c/(?<courseCode>[A-Za-z]{2,3}\\d{4}[A-Za-z]?)");
        argRegexMap.put("semester", "w/(?<semester>[1-8])");
        argRegexMap.put("mc", "m/(?<mc>[1-9]|1[0-2])");
        argRegexMap.put("grade", "g/(?<grade>[ab][+-]?|[cd][+]?|f|cs|cu)");
    }

    private String keyword;
    private String regex;
    private String[] groupArguments;
    private int regexLength;
    private Pattern pattern;

    protected CommandMetadata(String keyword, String regex, String[] groupArguments) {
        if (keyword == null || regex == null || groupArguments == null) {
            throw new IllegalArgumentException("Keyword, regex, and group arguments cannot be null");
        }
        this.keyword = keyword;
        this.regex = regex;
        this.groupArguments = groupArguments;

        this.regexLength = groupArguments.length + 1; // Keyword + number of Arguments
        this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    protected String getKeyword() {
        return keyword;
    }

    protected String getRegex() {
        return regex;
    }

    protected Pattern getPattern() {
        return pattern;
    }

    protected String[] getGroupArguments() {
        return groupArguments;
    }

    protected Map<String, String> getArgRegexMap() {
        return argRegexMap;
    }

    protected boolean matchesKeyword(String userInput) {
        if (userInput == null || userInput.isEmpty()) {
            return false;
        }

        userInput = userInput.trim();
        String[] userInputParts = userInput.split("\\s+");
        if (userInputParts[0].equalsIgnoreCase(keyword)) {
            return true;
        }
        return false;
    }

    protected Map<String, String> getCommandArguments(Matcher matcher) {
        Map<String, String> arguments = new HashMap<>();

        assert groupArguments != null : "groupArgument should be initialised at this point";
        for (String groupArgument : groupArguments) {
            String argument = matcher.group(groupArgument);
            if (argument == null) {
                continue;
            }

            if (groupArgument.equals("courseCode") || groupArgument.equals("grade")) {
                argument = argument.toUpperCase();
            }
            arguments.put(groupArgument, argument);
        }

        return arguments;
    }

    private void validateUserArguments(String argument, String argumentName) throws ParserException {
        String argRegex = argRegexMap.get(argumentName);
        assert argRegex != null : "Regex pattern for " + argumentName + " should already be placed in argRegexMap";

        Pattern pattern = Pattern.compile(argRegex);
        Matcher matcher = pattern.matcher(argument);

        if (!matcher.matches()) {
            logger.log(Level.INFO, "Regex pattern: " + argRegex);
            logger.log(Level.INFO, "UserInput Argument: " + argument);
            throw new ParserException(keyword + " command: Error in " + argumentName);
        }
    }

    protected void validateUserInput(String userInput) throws IllegalArgumentException, ParserException {
        assert userInput != null : "userInput should not be null at this point";

        String[] userInputParts = userInput.split("\\s+");
        assert userInputParts[0].equalsIgnoreCase(keyword) : "userInput should match keyword at this point";

        if (userInputParts.length != regexLength) {
            throw new ParserException(keyword + " command: Invalid argument length");
        }

        // Check user arguments
        if (groupArguments.length != userInputParts.length - 1) {
            throw new IllegalArgumentException("Regex length should be keyword + number of arguments");
        }
        for (int i = 0; i < groupArguments.length; i++) {
            validateUserArguments(userInputParts[i+1], groupArguments[i]);
        }
        return;
    }

    protected abstract Command createCommandInstance(Map<String, String> args);
}
