package supertracker.parser;

import supertracker.command.*;
import supertracker.item.Inventory;

public class Parser {
    private static final String QUIT_COMMAND = "quit";
    private static final String NEW_COMMAND = "new";
    private static final String LIST_COMMAND = "list";
    private static final String UPDATE_COMMAND = "update";
    private static final int PARAM_BEGIN_INDEX = 2;
    private static final double ROUNDING_FACTOR = 100.0;

    /**
     * Returns the command word specified in the user input string
     *
     * @param input a String of the user's input
     * @return a String of the first word in the user input
     */
    private static String getCommandWord(String input) {
        if (!input.contains(" ")) {
            return input;
        }
        return input.substring(0, input.indexOf(" "));
    }

    /**
     * Parses a Command accordingly from the user input string
     *
     * @param input a String of the user's input
     * @return a Command to execute
     */
    public static Command parseCommand(String input) {
        String commandWord = getCommandWord(input);

        Command command;
        switch (commandWord) {
        case QUIT_COMMAND:
            command = new QuitCommand();
            break;
        case NEW_COMMAND:
            command = parseNewCommand(input);
            break;
        case LIST_COMMAND:
            command = parseListCommand(input);
            break;
        case UPDATE_COMMAND:
            command = parseUpdateCommand(input);
            break;
        default:
            command = new InvalidCommand();
            break;
        }
        return command;
    }


    private static Command parseUpdateCommand(String input) {
        ExtractDataForNewOrUpdateCommand result = getExtractDataForNewOrUpdateCommand(input);

        if (result.name.isEmpty()) {
            // throw error
            System.out.println("empty param");
        }
        if (!Inventory.contains(result.name)) {
            // throw error
            System.out.println(result.name + " does not exist in inventory. Unable to update its values. =(");
        }

        int quantity = 0;
        double price = 0;

        if (result.quantityString.isEmpty()) {
            quantity = 0;
        }
        else {
            quantity = Integer.parseInt(result.quantityString);
        }
        if (result.priceString.isEmpty()) {
            price = 0;
        }
        else {
            price = roundTo2Dp(Double.parseDouble(result.priceString));
        }

        if (quantity < 0) {
            // throw error
            System.out.println("quantity less than 0");
        }
        if (price < 0) {
            // throw error
            System.out.println("price less than 0");
        }

        return new UpdateCommand(result.name, quantity, price);
    }

    // extracted for new or update command to use
    private static ExtractDataForNewOrUpdateCommand getExtractDataForNewOrUpdateCommand(String input) {
        // split input string into params if they start with "n/", "q/" or "p/"
        String[] params = input.split("(?=[nqp]/)");

        String name = "";
        String quantityString = "";
        String priceString = "";

        for (String param : params) {
            param = param.trim();
            if (param.startsWith("n/") && name.isEmpty()) {
                name = param.substring(PARAM_BEGIN_INDEX);
            } else if (param.startsWith("q/") && quantityString.isEmpty()) {
                quantityString = param.substring(PARAM_BEGIN_INDEX);
            } else if (param.startsWith("p/") && priceString.isEmpty()) {
                priceString = param.substring(PARAM_BEGIN_INDEX);
            }
        }
        return new ExtractDataForNewOrUpdateCommand(name, quantityString, priceString);
    }

    private static class ExtractDataForNewOrUpdateCommand {
        public final String name;
        public final String quantityString;
        public final String priceString;

        public ExtractDataForNewOrUpdateCommand(String name, String quantityString, String priceString) {
            this.name = name;
            this.quantityString = quantityString;
            this.priceString = priceString;
        }
    }

    private static Command parseNewCommand(String input) {
        ExtractDataForNewOrUpdateCommand result = getExtractDataForNewOrUpdateCommand(input);

        if (result.name.isEmpty() || result.quantityString.isEmpty() || result.priceString.isEmpty()) {
            // throw error (possible to split for unique error messages)
            System.out.println("empty param");
        }
        if (Inventory.contains(result.name)) {
            // throw error
            System.out.println("Inventory already contains item");
        }

        // throws NumberFormatException if strings cannot be parsed
        int quantity = Integer.parseInt(result.quantityString);
        double price = roundTo2Dp(Double.parseDouble(result.priceString));


        if (quantity < 0) {
            // throw error
            System.out.println("quantity less than 0");
        }
        if (price < 0) {
            // throw error
            System.out.println("price less than 0");
        }

        return new NewCommand(result.name, quantity, price);
    }

    private static Command parseListCommand(String input) {
        // split input string into params if they start with "q/" or "p/"
        String[] params = input.split("(?=[qp]/)");

        boolean qExists = false;
        boolean pExists = false;

        // to check if q comes before p or vice versa
        String firstParam = "";

        for (String param : params) {
            if (param.startsWith("q/")) {
                qExists = true;
                if (firstParam.isEmpty()) {
                    firstParam = "q";
                }
            } else if (param.startsWith("p/")) {
                pExists = true;
                if (firstParam.isEmpty()) {
                    firstParam = "p";
                }
            }
        }

        return new ListCommand(qExists, pExists, firstParam);
    }

    private static double roundTo2Dp(double unroundedValue) {
        return Math.round(unroundedValue * ROUNDING_FACTOR) / ROUNDING_FACTOR;
    }
}
