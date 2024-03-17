package seedu.duke.parser;

import java.util.function.Function;
import java.util.Map;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.duke.command.Command;
import seedu.duke.command.AddCommand;
import seedu.duke.modules.ModuleList;
// import seedu.duke.command.ListCommand;
// import seedu.duke.command.RemoveCommand;

public class Parser {

    // String Pattern inputs
    private static final Pattern INIT_PATTERN =
            Pattern.compile("init\\s+n/(?<name>[A-Za-z0-9 ]+)", Pattern.CASE_INSENSITIVE);
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

    // Argument Group captures
    private static final String[] INIT_ARGUMENTS = {"name"};
    private static final String[] GPA_ARGUMENTS = {};
    private static final String[] VIEW_ARGUMENTS = {"mode"};
    private static final String[] REMOVE_MODULE_ARGUMENTS = {"courseCode"};
    private static final String[] ADD_MODULE_ARGUMENTS = {"courseCode", "status", "semester", "mc"};
    private static final String[] GRADE_ARGUMENTS = {"courseCode", "grade"};

    // Command constructor function
    private static final Function<Map<String, String>, Command> INIT_CONSTRUCTOR = Parser::initCommand;
    private static final Function<Map<String, String>, Command> GPA_CONSTRUCTOR = Parser::gpaCommand;
    private static final Function<Map<String, String>, Command> VIEW_CONSTRUCTOR = Parser::listCommand;
    private static final Function<Map<String, String>, Command> REMOVE_MODULE_CONSTRUCTOR = Parser::removeCommand;
    private static final Function<Map<String, String>, Command> ADD_MODULE_CONSTRUCTOR = Parser::addCommand;
    private static final Function<Map<String, String>, Command> GRADE_CONSTRUCTOR = Parser::gradeCommand;

    // Initialise ArrayList that puts all the commandMetadata together
    private static final ArrayList<CommandMetadata> metadataList = initMetadataList();

    private static ArrayList<CommandMetadata> initMetadataList() {
        ArrayList<CommandMetadata> list = new ArrayList<>();

        list.add(new CommandMetadata(INIT_PATTERN, INIT_ARGUMENTS, INIT_CONSTRUCTOR));
        list.add(new CommandMetadata(GPA_PATTERN, GPA_ARGUMENTS, GPA_CONSTRUCTOR));
        list.add(new CommandMetadata(VIEW_PATTERN, VIEW_ARGUMENTS, VIEW_CONSTRUCTOR));
        list.add(new CommandMetadata(REMOVE_MODULE_PATTERN, REMOVE_MODULE_ARGUMENTS, REMOVE_MODULE_CONSTRUCTOR));
        list.add(new CommandMetadata(ADD_MODULE_PATTERN, ADD_MODULE_ARGUMENTS, ADD_MODULE_CONSTRUCTOR));
        list.add(new CommandMetadata(GRADE_PATTERN, GRADE_ARGUMENTS, GRADE_CONSTRUCTOR));

        return list;
    }

    public static void getCommand(String userInput) {
        for (CommandMetadata commandMetadata : metadataList) {
            Pattern commandPattern = commandMetadata.getPattern();
            Matcher matcher = commandPattern.matcher(userInput);

            if (matcher.matches()) {
                Map<String, String> commandArguments = commandMetadata.getCommandArguments(matcher);
                Function<Map<String, String>, Command> commandClassConstructor = commandMetadata.getConstructor();
                return;
            }
        }
        return;
    }

    // Class Constructor functions
    private static Command initCommand(Map<String, String> args) {
        // return new InitCommand(args);
        //to be continued by Fong Shi Xiang
        String moduleCode = "";
        String moduleGrade = "";
        String moduleMC = "";
        ModuleList moduleList = new ModuleList(2);
        return new AddCommand(moduleCode, moduleGrade, moduleMC, moduleList);
    }

    private static Command gpaCommand(Map<String, String> args) {
        // return new GPACommand();
        return new AddCommand();
    }

    private static Command listCommand(Map<String, String> args) {
        // return new ListCommand();
        return new AddCommand();
    }

    private static Command removeCommand(Map<String, String> args) {
        // return new RemoveCommand(args);
        return new AddCommand();
    }

    private static Command addCommand(Map<String, String> args) {
        // return new AddCommand(args);
        return new AddCommand();
    }

    private static Command gradeCommand(Map<String, String> args) {
        // return new GradeCommand();
        return new AddCommand();
    }
}
