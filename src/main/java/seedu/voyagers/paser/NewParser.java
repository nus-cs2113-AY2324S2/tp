package seedu.voyagers.paser;

import seedu.voyagers.commands.AddSubTripCommand;
import seedu.voyagers.commands.AddTripCommand;
import seedu.voyagers.commands.Command;
import seedu.voyagers.commands.DeleteCommand;
import seedu.voyagers.commands.EmptyCommand;
import seedu.voyagers.commands.ExitCommand;
import seedu.voyagers.commands.HelpCommand;
import seedu.voyagers.commands.ListCommand;
import seedu.voyagers.commands.ModifyTripCommand;
import seedu.voyagers.utils.FormatDate;

import seedu.voyagers.paser.ParserDefinitions;

import java.util.Arrays;

public class NewParser {

    /**
     * Parses the input from the user and returns the corresponding command.
     * @param line the input from the user
     * @return the corresponding command
     * @throws IllegalArgumentException if the input is invalid
     */
    public static Command parse(String line) throws IllegalArgumentException {
        String[] words = line.split(" ");
        String command = words[0];
        String[] values;

        String tripName="";
        String keyword="";

        //Check sintax
        try{
            tripName = words[1];
            keyword = words[1];
        }catch (ArrayIndexOutOfBoundsException e){
            //Do nothing
        }

        switch (command) {
        case "exit":
            return new ExitCommand();
        case "listall":
            return new ListCommand();
        case "deletemaintrip":
            return new DeleteCommand(parseArgs(line, ParserDefinitions.DELETE));
        case "addmaintrip":
            return new AddTripCommand(parseArgs(line, ParserDefinitions.ADDMAINTRIP));
        case "addsubtrip":
            return new AddSubTripCommand(parseArgs(line, ParserDefinitions.ADDSUBTRIP, true));
        case "setname":
            values = concatenate(new String[]{"name"},
                    parseArgs(line, ParserDefinitions.SETNAME, true));
            return new ModifyTripCommand(values);
        case "setdates":
            values = concatenate(new String[]{"dates"},
                    parseArgs(line, ParserDefinitions.SETDATES, true));
            return new ModifyTripCommand(values);
        case "setlocation":
            values = concatenate(new String[]{"location"},
                    parseArgs(line, ParserDefinitions.SETLOCATION, true));
            return new ModifyTripCommand(values);
        case "setdescription":
            values = concatenate(new String[]{"description"},
                    parseArgs(line, ParserDefinitions.SETDESCRIPTION, true));
            return new ModifyTripCommand(values);
        case "help":
            return new HelpCommand();
        default:
            throw new IllegalArgumentException("Invalid command");

        }
    }

    private static String[] concatenate(String[] s1, String[] s2){
        String[] result = new String[s1.length + s2.length];
        System.arraycopy(s1, 0, result, 0, s1.length);
        return result;
    }

    private static String[] parseArgs(String line, String[] validArgs) throws IllegalArgumentException {
        return parseArgs(line, validArgs, false);
    }

    /**
     * Extracts the arguments from an input line.
     * @param line the line to parse. command INITIAL /arg1 val 1 /arg2 val2 ...
     * @param validArgs expected arguments
     * @return the values for the arguments as an array. [Initial, /arg1, /arg2, ...]
     * @throws IllegalArgumentException if the arguments in the input are invalid
     */
    private static String[] parseArgs(String line, String[] validArgs, boolean initial) throws IllegalArgumentException {

        String[] args = line.split(" ");
        String[] values = new String[validArgs.length + 1];
        String aux = "";

        int i;
        int indexOfNewArgs = 0;
        int indexOfValidArgs = 0;
        int lenOfArgGiven = 0;

        for (i = 1; i < args.length; i++) {
            String word = args[i];
            if (word.equals(validArgs[Math.min(indexOfValidArgs, validArgs.length - 1)])) {
                if (lenOfArgGiven == 0 && indexOfValidArgs > 0) {
                    //String name = indexOfValidArgs > 0 ? validArgs[indexOfValidArgs - 1] : "description";
                    throw new IllegalArgumentException("No value for " + validArgs[indexOfValidArgs - 1]);
                }

                values[indexOfNewArgs++] = aux.trim();
                aux = "";
                indexOfValidArgs++;
                lenOfArgGiven = 0;
            } else if (word.startsWith("/")) {
                //We are forcing to receive the arguments in one order.
                //TODO: consider to allow the user to input the arguments in any order
                throw new IllegalArgumentException("Invalid argument " + word +
                        ". Expected " + validArgs[indexOfValidArgs]);
            } else {
                aux += word + " ";
                lenOfArgGiven++;
            }
        }

        values[indexOfNewArgs] = aux.trim();

        if (lenOfArgGiven == 0 && indexOfValidArgs > 0) {
            //String name = indexOfValidArgs > 0 ? validArgs[indexOfValidArgs - 1] : "description";
            throw new IllegalArgumentException("No value for " + validArgs[indexOfValidArgs - 1]);
        }

        if (indexOfValidArgs != validArgs.length) {
            String message = "Missing arguments. Expected ";
            for (String arg : validArgs) {
                message += arg + " ";
            }
            throw new IllegalArgumentException(message);
        }

        if (!initial){
            values = Arrays.copyOfRange(values, 1, values.length);
        }

        return values;
    }

    public  static void main(String[] args) {
        String line = "addmaintrip /n trip1 /start 2020-1-1 /end 2020-12-12 /location location1 /d description1";
        String line2 = "setname trip1 /n trip2";
        String[] values = null;
        try{
            values = parseArgs(line2, ParserDefinitions.SETNAME, true);}
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        for (String value : values) {
            System.out.println(value);
        }
        Command c = parse(line);

    }

}

