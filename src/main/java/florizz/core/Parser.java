package florizz.core;

import florizz.command.*;
import florizz.objects.Bouquet;

public class Parser {
    // prefixes to parse input

    private static final String QUANTITY = "/q";
    private static final String ADD_FLOWER_PREFIX = "/to";
    private static final String REMOVE_FLOWER_PREFIX = "/from";

    // regex
    private static final String ADD_FLOWER_REGEX = "(.+)/q(\\s*)(\\d+)(\\s*)/to(.+)";
    private static final String REMOVE_FLOWER_REGEX = "(.+)/q(\\s*)(\\d+)(\\s*)/from(.+)";

    public static Command parse (String input) throws FlorizzException{
        String[] decodedInput = commandHandler(input);
        switch (decodedInput[0]){
        case ("mybouquets"):
            return new ListBouquetCommand();
        case ("new"):
            return handleAddBouquet(input);
        case ("delete"):
            return handleDeleteBouquet(input);
        case ("bye"):
            return new ExitCommand();
        case ("help"):
            return new HelpCommand();
        case ("flower"):
            return handleFlowerCommand(input);
        case ("info"):
              return handleInfoCommand(input);      
        case ("occasion"):
            return new ListOccasionCommand();
        case ("add"):
            return handleAddFlower(decodedInput[1]);
        case ("remove"):
            return handleRemoveFlower(decodedInput[1]);
        default:
            throw new FlorizzException("Unidentified input, type help to get a list of all commands!");
        }
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
        String occasion  = (input.length() == 6) ? " " : input.substring(input.indexOf(" ") + 1);
        return new FlowerCommand(occasion);
    }

    private static AddFlowerCommand handleAddFlower(String argument) throws FlorizzException {
        if (argument == null) {
            throw new FlorizzException("No argument detected! Please use the correct format of 'add <flowerName> /q <quantity> /to <bouquetName>");
        }

        if (!argument.matches(ADD_FLOWER_REGEX)) {
            throw new FlorizzException("Incorrect format detected! Please use the correct format of 'add <flowerName> /q <quantity> /to <bouquetName>");
        }

        // [WARNING] might need to check for extra slash k

        int prefixIndex = argument.indexOf(ADD_FLOWER_PREFIX);
        int quantityIndex = argument.indexOf(QUANTITY);

        String flowerName = argument.substring(0,quantityIndex).trim();
        String quantityString = removePrefix(argument.substring(quantityIndex, prefixIndex), QUANTITY).trim();
        // [WARNING] might need to check if it's a valid integer
        Integer quantity = Integer.parseInt(quantityString);
        String bouquetName = removePrefix(argument.substring(prefixIndex), ADD_FLOWER_PREFIX).trim();

        return new AddFlowerCommand(flowerName, quantity, bouquetName);
    }

    private static RemoveFlowerCommand handleRemoveFlower(String argument) throws FlorizzException {
        if (argument == null) {
            throw new FlorizzException("No argument detected! Please use the correct format of 'remove <flowerName> /q <quantity> /from <bouquetName>");
        }

        if (!argument.matches(REMOVE_FLOWER_REGEX)) {
            throw new FlorizzException("Incorrect format detected! Please use the correct format of 'remove <flowerName> /q <quantity> /from <bouquetName>");
        }

        // [WARNING] might need to check for extra slash k

        int prefixIndex = argument.indexOf(REMOVE_FLOWER_PREFIX);
        int quantityIndex = argument.indexOf(QUANTITY);

        String flowerName = argument.substring(0,quantityIndex).trim();
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
