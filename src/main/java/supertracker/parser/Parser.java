package supertracker.parser;

import supertracker.command.Command;
import supertracker.command.NewCommand;
import supertracker.command.QuitCommand;
import supertracker.command.InvalidCommand;
import supertracker.command.ListCommand;
import supertracker.item.Inventory;

public class Parser {
    private static final String QUIT_COMMAND = "quit";
    private static final String NEW_COMMAND = "new";
    private static final String LIST_COMMAND = "list";
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
            command = new ListCommand();
            break;
        default:
            command = new InvalidCommand();
            break;
        }
        return command;
    }

    private static Command parseNewCommand(String input) {
        // split input string into params if they start with "n/", "q/" or "p/"
        String[] params = input.split("(?=[nqp]/)");

        String name = "";
        String quantityString = "";
        String priceString = "";

        for (String param : params) {
            param = param.trim();
            if (param.startsWith("n/")) {
                name = param.substring(PARAM_BEGIN_INDEX);
            } else if (param.startsWith("q/")) {
                quantityString = param.substring(PARAM_BEGIN_INDEX);
            } else if (param.startsWith("p/")) {
                priceString = param.substring(PARAM_BEGIN_INDEX);
            }
        }

        if (name.isEmpty() || quantityString.isEmpty() || priceString.isEmpty()) {
            // throw error (possible to split for unique error messages)
            System.out.println("empty param");
        }

        if (Inventory.contains(name)) {
            // throw error
            System.out.println("Inventory already contains item");
        }

        // throws NumberFormatException if strings cannot be parsed
        int quantity = Integer.parseInt(quantityString);
        double price = roundTo2Dp(Double.parseDouble(priceString));


        if (quantity < 0) {
            // throw error
            System.out.println("quantity less than 0");
        }
        if (price < 0) {
            // throw error
            System.out.println("price less than 0");
        }

        return new NewCommand(name, quantity, price);
    }

    private static double roundTo2Dp(double unroundedValue) {
        return Math.round(unroundedValue * ROUNDING_FACTOR) / ROUNDING_FACTOR;
    }
}
