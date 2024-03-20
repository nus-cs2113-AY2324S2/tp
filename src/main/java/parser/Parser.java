package parser;

import command.*;
import common.Messages;
import exceptions.CommandFormatException;
import item.Item;
import itemlist.Itemlist;
import storage.Storage;
import ui.TextUi;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static final Pattern ADD_COMMAND_FORMAT =
            Pattern.compile("add (?<itemName>[^/]+) qty/(?<quantity>\\d+) /(?<uom>[^/]+)");

    public static final Pattern DELETE_COMMAND_FORMAT =
            Pattern.compile("del (?<itemName>[^/]+)");

    public static final Pattern EDIT_COMMAND_FORMAT =
            Pattern.compile("edit (?<itemName>[^/]+) qty/(?<newQuantity>\\d+)");

    public static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");


    public Command parseInput(String userInput) {

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            System.out.println(Messages.INVALID_COMMAND);
            System.out.println(Messages.HELP);
        }

        final CommandType userCommand = CommandType.valueOf(matcher.group("commandWord").toUpperCase());
        final String arguments = matcher.group("arguments");


        switch (userCommand) {
        case EXIT:
//          storage.saveItems(items);  //(COMMAND FROM STORAGE TO SAVE ITEMLIST)
//          ui.closeScanner();  //(COMMAND FROM TEXTUI TO CLOSE SCANNER)
            return new ExitCommand(true);
        case HELP:
            return new HelpCommand();
            break;
        case LIST:
            return new ListCommand<>(Itemlist.getItems());
            break;

        case ADD:
            try {
                return prepareAdd(arguments);
            } catch (CommandFormatException e) {
                break;
            }
            break;
        case DELETE:
            try {
                return prepareDelete(arguments);
            } catch (CommandFormatException e) {
                break;
            }
            break;
        case EDIT:
           try {
               return prepareEdit(arguments);
            } catch (CommandFormatException e) {
                break;
            }
            break;
        default:
            System.out.println(Messages.INVALID_COMMAND);
            break;
        }
    }


    private Command prepareAdd(String args) throws CommandFormatException{
        final Matcher matcher = ADD_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.ADD);
            return IncorrectCommand;
        }
        try {
            return new AddCommand(
                    matcher.group("itemName"),
                    matcher.group("quantity"),
                    matcher.group("uom")
            );

        } catch (CommandFormatException e) {
            throw new CommandFormatException(CommandType.ADD);
            return IncorrectCommand;
        }
    }

    private Command prepareDelete(String args) throws CommandFormatException{
        final Matcher matcher = DELETE_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.DELETE);
            return IncorrectCommand;
        }
        try {
            return new DeleteCommand(matcher.group("itemName");

        } catch (CommandFormatException e) {
            throw new CommandFormatException(CommandType.DELETE);
            return IncorrectCommand;
        }
    }

    private Command prepareEdit(String args) throws CommandFormatException{
        final Matcher matcher = EDIT_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.EDIT);
            return IncorrectCommand;
        }
        try {
            return new EditCommand(
                    matcher.group("itemName"),
                    matcher.group("newQuantity")
            );

        } catch (CommandFormatException e) {
            throw new CommandFormatException(CommandType.EDIT);
            return IncorrectCommand;
        }
    }
}



