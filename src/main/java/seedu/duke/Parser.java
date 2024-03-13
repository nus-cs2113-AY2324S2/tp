package seedu.binbash.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.binbash.ItemList;
import seedu.binbash.command.Command;
import seedu.binbash.command.DeleteCommand;
import seedu.binbash.command.ListCommand;

public class Parser {
    private final ItemList itemList;

    public Parser(ItemList itemList) {
        this.itemList = itemList;
    }

    public Command parseCommand(String userInput) {
        String[] tokens = userInput.trim().split("\\s+", 2);
        String commandString = tokens[0].toLowerCase();
        String arguments = tokens.length > 1 ? tokens[1] : "";

        switch (commandString) {
            case "delete":
                return parseDeleteCommand(arguments);
            case "list":
                return parseListCommand(arguments);
            default:
                return null;
        }
    }

    private Command parseDeleteCommand(String arguments) {
        Matcher matcher = DeleteCommand.COMMAND_FORMAT.matcher(arguments);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group("index"));
            return new DeleteCommand(itemList, index);
        } else {
            return null;
        }
    }

    private Command parseListCommand(String arguments) {
        return new ListCommand(itemList);
    }
}
