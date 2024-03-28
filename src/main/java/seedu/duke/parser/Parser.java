package seedu.duke.parser;

import seedu.duke.exceptions.ParserException;
import seedu.duke.command.Command;
import seedu.duke.command.InvalidCommand;

import java.util.Map;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class Parser {

    private static ArrayList<CommandMetadata> metadataList = new ArrayList<>();
    static {
        metadataList.add(new InitCommandMetadata());
        metadataList.add(new GpaCommandMetadata());
        metadataList.add(new ViewCommandMetadata());
        metadataList.add(new ViewGraduateCommandMetadata());
        metadataList.add(new AddCommandMetadata());
        metadataList.add(new RemoveCommandMetadata());
        metadataList.add(new GradeCommandMetadata());
        metadataList.add(new ByeCommandMetadata());
    }

    public static Command getCommand(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            return new InvalidCommand();
        }

        userInput = userInput.trim();

        for (CommandMetadata commandMetadata : metadataList) {
            if (!commandMetadata.matchesKeyword(userInput)) {
                continue; // Skip metadata if keyword doesn't match
            }
            try {
                Matcher matcher = commandMetadata.getPattern().matcher(userInput);
                if (matcher.matches()) {
                    Map<String, String> commandArguments = commandMetadata.getCommandArguments(matcher);
                    Command commandInstance = commandMetadata.createCommandInstance(commandArguments);
                    return commandInstance;
                }
                commandMetadata.validateUserInput(userInput);
            } catch (ParserException e) {
                return new InvalidCommand(e.getMessage());
            }
        }
        return new InvalidCommand();
    }
}
