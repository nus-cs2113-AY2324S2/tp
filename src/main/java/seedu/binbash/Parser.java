package seedu.binbash;

import java.util.regex.Matcher;

import seedu.binbash.command.AddCommand;
import seedu.binbash.command.ByeCommand;
import seedu.binbash.command.Command;
import seedu.binbash.command.DeleteCommand;

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
        case "add":
            return parseAddCommand(userInput);
        case "delete":
            return parseDeleteCommand(userInput);
        case "list":
            return parseListCommand(userInput);
        default:
            return new ByeCommand(itemList);
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

    private Command parseAddCommand(String arguments) {
        Matcher matcher = AddCommand.COMMAND_FORMAT.matcher(arguments);
        if (matcher.matches()) {
            String itemName = matcher.group("itemName");
            String itemDescription = matcher.group("itemDescription");
            return new AddCommand(itemList, itemName, itemDescription);
        } else {
            return null;
        }
    }

    private Command parseListCommand(String arguments) {
        return new ListCommand(itemList);
    }
}

