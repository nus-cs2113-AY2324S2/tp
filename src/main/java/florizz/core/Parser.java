package florizz.core;

import florizz.command.Command;
import florizz.command.AddBouquetCommand;
import florizz.command.AddFlowerCommand;
import florizz.command.DeleteBouquetCommand;
import florizz.command.ExitCommand;
import florizz.command.FlowerCommand;
import florizz.command.InfoCommand;
import florizz.command.ListBouquetCommand;
import florizz.command.ListOccasionCommand;
import florizz.command.RemoveFlowerCommand;
import florizz.command.HelpCommand;
import florizz.objects.Bouquet;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {
    private static Logger logger = Logger.getLogger(Florizz.class.getName());
    // prefixes to parse input
    private static final String QUANTITY = "/q";
    private static final String ADD_FLOWER_PREFIX = "/to";
    private static final String REMOVE_FLOWER_PREFIX = "/from";

    // regex
    private static final String ADD_FLOWER_REGEX = "(.+)/q(\\s*)(\\d+)(\\s*)/to(.+)";
    private static final String REMOVE_FLOWER_REGEX = "(.+)/q(\\s*)(\\d+)(\\s*)/from(.+)";

    public static Command parse (String input) throws FlorizzException {
        logger.entering("Parser", "parse");
        Command command = null;

        try {
            String[] decodedInput = commandHandler(input);
            //logger.log(Level.INFO, "commandHandler handled command successfully");
            switch (decodedInput[0]) {
            case ("mybouquets"):
                command = new ListBouquetCommand();
                break;
            case ("new"):
                command = handleAddBouquet(input);
                break;
            case ("delete"):
                command = handleDeleteBouquet(input);
                break;
            case ("bye"):
                command = new ExitCommand();
                break;
            case ("help"):
                command = new HelpCommand();
                break;
            case ("flowers"):
                command = handleFlowerCommand(input);
                break;
            case ("info"):
                command = handleInfoCommand(input);
                break;
            case ("occasion"):
                command = new ListOccasionCommand();
                break;
            case ("add"):
                command = handleAddFlower(decodedInput[1]);
                break;
            case ("remove"):
                command = handleRemoveFlower(decodedInput[1]);
                break;
            default:
                throw new FlorizzException("Unidentified input, type help to get a list of all commands!");
            }
            logger.log(Level.INFO, "Command parsed successfully");
        } catch (FlorizzException ex) {
            logger.log(Level.SEVERE, "Exception occurred while parsing command: " + ex.errorMessage, ex);
            throw ex;
        } finally {
            logger.exiting("Parser", "parse");
        }
        return command;
    }


    /**
     * Splits input into command and arguments. Also handles capitalisation and space exceptions
     *
     * @param input
     * @return String[] output; output[0] = command ; output[1] arguments
     */
    private static String[] commandHandler(String input) {
        String[] output = new String[2];
        String trimmedInput = input.trim();
        int firstWhitespace = trimmedInput.indexOf(" ");
        if (firstWhitespace != -1) {
            // input have arguments
            output[0] = trimmedInput.substring(0,firstWhitespace).toLowerCase();
            output[1] = trimmedInput.substring(firstWhitespace).trim();
        } else {
            output[0] = trimmedInput.toLowerCase();
        }
        return output;
    }

    /**
     * remove prefix from an input string
     * e.g. "/to For Mom" -> " For Mom"
     *
     * @param input
     * @param prefix
     * @return input with prefix removed
     */
    private static String removePrefix(String input, String prefix) {
        return input.replace(prefix, "");
    }
  
    private static AddBouquetCommand handleAddBouquet(String input) throws FlorizzException{
        if (!input.contains(" ")){
            throw new FlorizzException("Did not include bouquet to add");
        }
        String newBouquetName = input.substring(input.indexOf(" ") + 1).trim();
        return new AddBouquetCommand(new Bouquet(newBouquetName));
    }

    private static DeleteBouquetCommand handleDeleteBouquet(String input) throws FlorizzException{
        if (!input.contains(" ")){
            throw new FlorizzException("Did not include bouquet to delete");
        }
        String bouquetToDelete = input.substring(input.indexOf(" ") + 1).trim();

        return new DeleteBouquetCommand(new Bouquet(bouquetToDelete));
    }

    private static FlowerCommand handleFlowerCommand(String input) {
        String occasion  = (input.length() == 7) ? " " : input.substring(input.indexOf(" ") + 1);
        return new FlowerCommand(occasion);
    }

    private static AddFlowerCommand handleAddFlower(String argument) throws FlorizzException {
        if (argument == null) {
            throw new FlorizzException("No argument detected! " +
                    "Please use the correct format of 'add <flowerName> /q <quantity> /to <bouquetName>");
        }

        if (!argument.matches(ADD_FLOWER_REGEX)) {
            throw new FlorizzException("Incorrect format detected! " +
                    "Please use the correct format of 'add <flowerName> /q <quantity> /to <bouquetName>");
        }

        // [WARNING] might need to check for extra slash k

        int prefixIndex = argument.indexOf(ADD_FLOWER_PREFIX);
        int quantityIndex = argument.indexOf(QUANTITY);

        String flowerName = argument.substring(0,quantityIndex).trim().toLowerCase();
        String quantityString = removePrefix(argument.substring(quantityIndex, prefixIndex), QUANTITY).trim();
        // [WARNING] might need to check if it's a valid integer
        Integer quantity = Integer.parseInt(quantityString);
        String bouquetName = removePrefix(argument.substring(prefixIndex), ADD_FLOWER_PREFIX).trim();

        return new AddFlowerCommand(flowerName, quantity, bouquetName);
    }

    private static RemoveFlowerCommand handleRemoveFlower(String argument) throws FlorizzException {
        if (argument == null) {
            throw new FlorizzException("No argument detected! " +
                    "Please use the correct format of 'remove <flowerName> /q <quantity> /from <bouquetName>");
        }

        if (!argument.matches(REMOVE_FLOWER_REGEX)) {
            throw new FlorizzException("Incorrect format detected! " +
                    "Please use the correct format of 'remove <flowerName> /q <quantity> /from <bouquetName>");
        }

        // [WARNING] might need to check for extra slash k

        int prefixIndex = argument.indexOf(REMOVE_FLOWER_PREFIX);
        int quantityIndex = argument.indexOf(QUANTITY);


        String flowerName = argument.substring(0, quantityIndex).trim().toLowerCase();
        String quantityString = removePrefix(argument.substring(quantityIndex, prefixIndex), QUANTITY).trim();
        // [WARNING] might need to check if it's a valid integer
        Integer quantity = Integer.parseInt(quantityString);
        String bouquetName = removePrefix(argument.substring(prefixIndex), REMOVE_FLOWER_PREFIX).trim();

        return new RemoveFlowerCommand(flowerName, quantity, bouquetName);
    }

    private static InfoCommand handleInfoCommand(String input) {
        String flowerName = input.substring(input.indexOf(" ") + 1);
        assert !flowerName.isEmpty() : "This string is empty";
        return new InfoCommand(flowerName);

    }
}
