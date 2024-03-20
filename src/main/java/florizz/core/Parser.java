package florizz.core;

import florizz.command.*;
import florizz.objects.Bouquet;

public class Parser {
    // prefixes to parse input
    private static final String ADD_FLOWER_PREFIX = "/to";
    private static final String REMOVE_FLOWER_PREFIX = "/from";

    // regex
    private static final String ADD_FLOWER_REGEX = ".+/from\\s*.+";

    // count of argument
    //private static final int ADD_FLOWER_

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
        case ("occasion"):
            return new ListOccasionCommand();
        //case ("add"):
        //    return handleAddFlower(decodedInput[1]);
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

    private static AddBouquetCommand handleAddBouquet(String input) {
        String newBouquetName = input.substring(input.indexOf(" ") + 1);
        return new AddBouquetCommand(new Bouquet(newBouquetName));
    }

    private static DeleteBouquetCommand handleDeleteBouquet(String input) {
        String bouquetToDelete = input.substring(input.indexOf(" ") + 1);
        return new DeleteBouquetCommand(new Bouquet(bouquetToDelete));
    }

    private static FlowerCommand handleFlowerCommand(String input) {
        String occasion  = (input.length() == 6) ? " " : input.substring(input.indexOf(" ") + 1);
        return new FlowerCommand(occasion);
    }


    /*
    private static AddFlowerCommand handleAddFlower(String input) {
        String arguments =
        int indexOfAddFlowerPrefix = input.indexOf(ADD_FLOWER_PREFIX);
        String flowerName= input.substring(input.indexOf(" ") + 1)
        String bouquetName =
    }*/

}
