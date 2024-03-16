package seedu.binbash;

import java.util.regex.Matcher;

import seedu.binbash.command.*;

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
        case "search":
            return parseSearchCommand(userInput);
        default:
            return new ByeCommand(itemList);
        }
    }

    private Command parseDeleteCommand(String userInput) {
        Matcher matcher = DeleteCommand.COMMAND_FORMAT.matcher(userInput);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group("index"));
            return new DeleteCommand(itemList, index);
        } else {
            return null;
        }
    }

    private Command parseAddCommand(String userInput) {
        Matcher matcher = AddCommand.COMMAND_FORMAT.matcher(userInput);
        if (matcher.matches()) {
            String itemName = matcher.group("itemName");
            String itemDescription = matcher.group("itemDescription");
            return new AddCommand(itemList, itemName, itemDescription);
        } else {
            return null;
        }
    }

    private Command parseSearchCommand(String userInput) {
        Matcher matcher = SearchCommand.COMMAND_FORMAT.matcher(userInput);
        if (matcher.matches()) {
            String keyword = matcher.group("keyword");
            return new SearchCommand(itemList, keyword);
        } else {
            return null;
        }
    }

    private Command parseListCommand(String arguments) {
        return new ListCommand(itemList);
    }
}

