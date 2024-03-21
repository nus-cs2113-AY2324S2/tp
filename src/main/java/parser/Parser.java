package parser;

import command.Command;
import command.DeleteCommand;
import command.AddCommand;
import command.ListCommand;
import command.IncorrectCommand;
import command.ExitCommand;
import command.HelpCommand;
import command.EditCommand;
import common.Messages;
import exceptions.CommandFormatException;
import itemlist.Itemlist;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static final Pattern ADD_COMMAND_FORMAT =
            //Pattern.compile("add (?<itemName>[^/]+) qty/(?<quantity>\\d+) /(?<uom>[^/]+) (?: cat/(?<category>[^/]+))?");
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


        switch (userCommand) {
        case EXIT:
            return new ExitCommand(true);
        case HELP:
            return new HelpCommand();
        case LIST:
            return new ListCommand<>(Itemlist.getItems());
        case ADD:
            try {
                return prepareAdd(userInput);

            } catch (CommandFormatException e) {
                break;
            }
        case DELETE:
            try {
                return prepareDelete(userInput);
            } catch (CommandFormatException e) {
                break;
            }
        case EDIT:
            try {
                return prepareEdit(userInput);
            } catch (CommandFormatException e) {
                break;
            }
        default:
            System.out.println(Messages.INVALID_COMMAND);
            break;
        }
        return new IncorrectCommand();
    }


    private Command prepareAdd(String args) throws CommandFormatException{
        final Matcher matcher = ADD_COMMAND_FORMAT.matcher(args.trim());

        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.ADD);
        }
        //String category = matcher.group("category") != null ? matcher.group("category") : "NA";
        int quantity = Integer.parseInt(matcher.group("quantity"));
        return new AddCommand(
                matcher.group("itemName"),
                quantity,
                matcher.group("uom"), "test cat"
                //category
        );
    }

    private Command prepareDelete(String args) throws CommandFormatException{
        final Matcher matcher = DELETE_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.DELETE);
        }
        return new DeleteCommand(matcher.group("itemName"));
    }

    private Command prepareEdit(String args) throws CommandFormatException{
        final Matcher matcher = EDIT_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandFormatException(CommandType.EDIT);
        }
        int newQuantity = Integer.parseInt(matcher.group("newQuantity"));
        return new EditCommand(
            matcher.group("itemName"),
            newQuantity
        );
    }
}



