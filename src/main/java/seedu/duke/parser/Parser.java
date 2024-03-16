package seedu.duke.parser;

import java.util.function.Function;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.duke.command.Command;
// import seedu.duke.command.AddCommand;
// import seedu.duke.command.ListCommand;
// import seedu.duke.command.RemoveCommand;

public class Parser {

    enum CommandType {
        INIT,
        GPA,
        VIEW,
        REMOVE_MODULE,
        ADD_MODULE,
        GRADE,
        INVALID
    }

    private static final Pattern INIT_PATTERN =
            Pattern.compile("init", Pattern.CASE_INSENSITIVE);
    private static final Pattern GPA_PATTERN =
            Pattern.compile("gpa", Pattern.CASE_INSENSITIVE);
    private static final Pattern VIEW_PATTERN =
            Pattern.compile("view\\s+(?<mode>all|taken|plan)", Pattern.CASE_INSENSITIVE);
    private static final Pattern REMOVE_MODULE_PATTERN =
            Pattern.compile("remove\\s+c/(?<courseCode>[A-Za-z]{2,3}\\d{4}[A-Za-z]?)", Pattern.CASE_INSENSITIVE);
    private static final Pattern ADD_MODULE_PATTERN =
            Pattern.compile("add\\s+c/(?<courseCode>[A-Za-z]{2,3}\\d{4}[A-Za-z]?)\\s+s/(?<status>plan|taken)" +
                    "\\s+w/(?<semester>[1-9]|10)\\s+m/(?<mc>[1-9]|1[0-2])", Pattern.CASE_INSENSITIVE);
    private static final Pattern GRADE_PATTERN =
            Pattern.compile("grade\\s+c/(?<courseCode>[A-Za-z]{2,3}\\d{4}[A-Za-z]?)" +
                    "\\s+g/(?<grade>[ab][+-]?|[cd][+]?|f|[1-5](?:\\.0|\\.5)?|0\\.0?)", Pattern.CASE_INSENSITIVE);

    private static Map<CommandType, Pattern> commandPatternMap = initCommandPatternMap();
    private static Map<CommandType, String[]> commandGroupArgumentMap = initCommandGroupArgumentMap();
    private static Map<CommandType, Function<Map<String, String>, Command>> commandClassMap = initCommandClassMap();

    private static Map<CommandType, Pattern> initCommandPatternMap() {
        Map<CommandType, Pattern> commandPatternMap = new HashMap<>();

        commandPatternMap.put(CommandType.INIT, INIT_PATTERN);
        commandPatternMap.put(CommandType.GPA, GPA_PATTERN);
        commandPatternMap.put(CommandType.GRADE, GRADE_PATTERN);
        commandPatternMap.put(CommandType.VIEW, VIEW_PATTERN);
        commandPatternMap.put(CommandType.REMOVE_MODULE, REMOVE_MODULE_PATTERN);
        commandPatternMap.put(CommandType.ADD_MODULE, ADD_MODULE_PATTERN);

        return commandPatternMap;
    }

    private static Map<CommandType, String[]> initCommandGroupArgumentMap() {
        Map<CommandType, String[]> commandGroupArgumentMap = new HashMap<>();

        commandGroupArgumentMap.put(CommandType.INIT, new String[]{});
        commandGroupArgumentMap.put(CommandType.GPA, new String[]{});
        commandGroupArgumentMap.put(CommandType.GRADE, new String[]{"courseCode", "grade"});
        commandGroupArgumentMap.put(CommandType.VIEW, new String[]{"mode"});
        commandGroupArgumentMap.put(CommandType.REMOVE_MODULE, new String[]{"courseCode"});
        commandGroupArgumentMap.put(CommandType.ADD_MODULE, new String[]{"courseCode", "status", "semester", "mc"});

        return commandGroupArgumentMap;
    }

    private static Map<CommandType, Function<Map<String, String>, Command>> initCommandClassMap() {
        Map<CommandType, Function<Map<String, String>, Command>> commandClassMap = new HashMap<>();

        // CURRENTLY UNAVAILABLE: Command Classes to execute
        // commandClassMap.put(CommandType.INIT, args -> new InitCommand());
        // commandClassMap.put(CommandType.GPA, args -> new GPACommand());
        // commandClassMap.put(CommandType.GRADE, args -> new GradeCommand(args));
        // commandClassMap.put(CommandType.ADD_MODULE, args -> new AddCommand(args));
        // commandClassMap.put(CommandType.VIEW, args -> new ListCommand(args));
        // commandClassMap.put(CommandType.REMOVE_MODULE, args -> new RemoveCommand(args));

        return commandClassMap;
    }

    public static void getCommand(String userInput) {
        for (Map.Entry<CommandType, Pattern> entry : commandPatternMap.entrySet()) {
            Pattern commandPattern = entry.getValue();
            Matcher matcher = commandPattern.matcher(userInput);

            if (matcher.matches()) {
                CommandType commandType = entry.getKey();
                // Function<Map<String, String>, Command> commandCreator = commandClassMap.get(commandType);
                Map<String, String> arguments = getCommandArguments(commandType, matcher);

                // return commandCreator.apply(arguments);
                return;
            }
        }
        return;
    }

    private static Map<String, String> getCommandArguments(CommandType command, Matcher matcher) {
        String[] groupNames = commandGroupArgumentMap.get(command);
        Map<String, String> arguments = new HashMap<>();

        for (int i = 0; i < groupNames.length; i++) {
            arguments.put(groupNames[i], matcher.group(groupNames[i]));
        }

        return arguments;
    }
}
