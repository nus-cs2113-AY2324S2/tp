package seedu.binbash.parser;

import org.apache.commons.cli.ParseException;
import seedu.binbash.command.AddCommand;
import seedu.binbash.command.ByeCommand;
import seedu.binbash.command.Command;
import seedu.binbash.command.DeleteCommand;
import seedu.binbash.command.ListCommand;
import seedu.binbash.command.SearchCommand;
import seedu.binbash.exceptions.BinBashException;
import seedu.binbash.exceptions.InvalidArgumentException;
import seedu.binbash.exceptions.InvalidCommandException;
import seedu.binbash.exceptions.InvalidFormatException;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    protected static final DateTimeFormatter EXPECTED_INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private AddCommandParser addCommandParser;
    private RestockCommandParser restockCommandParser;
    private SellCommandParser sellCommandParser;

    public Parser() {
        addCommandParser = new AddCommandParser();
        restockCommandParser = new RestockCommandParser();
        sellCommandParser = new SellCommandParser();
    }

    public Command parseCommand(String userInput) throws BinBashException {
        String[] tokens = userInput.trim().split("\\s+"); // Tokenize user input
        String commandString = tokens[0].toLowerCase();
        String[] commandArgs = Arrays.copyOfRange(tokens, 1, tokens.length); // Takes only options and arguments

        switch (commandString) {
        case "bye":
        case "exit":
        case "quit":
            return new ByeCommand();
        case "add":
            return parseAddCommand(commandArgs);
        case "delete":
            return parseDeleteCommand(userInput);
        case "list":
            return parseListCommand();
        case "search":
            return parseSearchCommand(userInput);
        case "restock":
            return parseRestockCommand(commandArgs);
        case "sell":
            return parseSellCommand(commandArgs);
        default:
            throw new InvalidCommandException("Invalid command!");
        }
    }

    private Command parseDeleteCommand(String userInput) throws InvalidFormatException,
            InvalidArgumentException {
        Matcher argumentMatcher = DeleteCommand.COMMAND_FORMAT.matcher(userInput);
        if (!argumentMatcher.matches()) {
            throw new InvalidFormatException("Delete command is not properly formatted!");
        }

        Pattern indexIdentifier = Pattern.compile("^-?[0-9]+$");
        Matcher indexMatcher = indexIdentifier.matcher(argumentMatcher.group("identifier"));

        if (indexMatcher.matches()) {
            int index = Integer.parseInt(argumentMatcher.group("identifier"));
            if (index < 0) {
                throw new InvalidArgumentException("Task index cannot be negative!");
            }
            return new DeleteCommand(index);
        } else {
            String keyword = argumentMatcher.group("identifier");
            assert !keyword.isEmpty();
            return new DeleteCommand(keyword);
        }
    }

    private AddCommand parseAddCommand(String[] commandArgs) throws InvalidFormatException {
        try {
            return addCommandParser.parse(commandArgs);
        } catch (ParseException e) {
            throw new InvalidFormatException(e.getMessage());
        }
    }

    private Command parseRestockCommand(String[] commandArgs) throws InvalidFormatException {
        try {
            return restockCommandParser.parse(commandArgs);
        } catch (ParseException e) {
            throw new InvalidFormatException(e.getMessage());
        }
    }

    private Command parseSellCommand(String[] commandArgs) throws InvalidFormatException {
        try {
            return sellCommandParser.parse(commandArgs);
        } catch (ParseException e) {
            throw new InvalidFormatException(e.getMessage());
        }
    }

    private Command parseSearchCommand(String userInput) throws InvalidFormatException {
        Matcher matcher = SearchCommand.COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new InvalidFormatException("Search command is not properly formatted!");
        }
        String keyword = matcher.group("keyword");
        return new SearchCommand(keyword);
    }

    private Command parseListCommand() {
        return new ListCommand();
    }
}
