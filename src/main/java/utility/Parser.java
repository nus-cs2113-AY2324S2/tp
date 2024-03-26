package utility;

import ui.Output;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the parser used for PulsePilot
 */
public class Parser {

    /**
     * Parses and converts String date to a LocalDate variable.
     * @param date String representing the date.
     * @return LocalDate variable representing the date.
     *
     * @throws DateTimeParseException If there is an error parsing the date.
     */
    public static LocalDate parseDate(String date) {
        // try {
        //     checkDateInput(date);
        // } catch (CustomExceptions.InvalidInput e) {
        //     throw new CustomExceptions.InvalidInput("Invalid date format");
        // }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate formattedDate = null;
        try {
            formattedDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            Output.printException("Error parsing date!");
        }
        return formattedDate;
    }

    /**
     * Validates that the input date string is correctly formatted in DD-MM-YYYY.
     *
     * @param date The string date from user input.
     * @throws CustomExceptions.InvalidInput If there are invalid date inputs.
     */
    public static void checkDateInput(String date) throws CustomExceptions.InvalidInput {
        String[] parts = getDateStrings(date);

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        if (day < 1 || day > 31) {
            throw new CustomExceptions.InvalidInput("Day must be an integer between 01 and 31.");
        }

        if (month < 1 || month > 12) {
            throw new CustomExceptions.InvalidInput("Month must be an integer between 01 and 12.");
        }

        if (day <= 10 && !parts[0].startsWith("0")) {
            throw new CustomExceptions.InvalidInput("Day must start with '0' if day is less than 10");
        }

    }

    /**
     * Splits the date string input into day, month and year.
     *
     * @param date The string date from user input.
     * @return A list of strings representing day, month and year
     * @throws CustomExceptions.InvalidInput If there are invalid date inputs.
     */
    private static String[] getDateStrings(String date) throws CustomExceptions.InvalidInput {
        String [] parts;
        try {
            parts = date.split("-");
        } catch (Exception e) {
            throw new CustomExceptions.InvalidInput("Invalid delimiter. Format is DD-MM-YYYY");
        }

        if (parts.length != 3){
            throw new CustomExceptions.InvalidInput("Insufficient date parameters. Format is DD-MM-YYYY");
        }

        if (parts[0].length() != 2 || parts[1].length() != 2 || parts[2].length() != 4) {
            throw new CustomExceptions.InvalidInput("Invalid date format. Format is DD-MM-YYYY");
        }
        return parts;
    }

    /**
     * Parses and validates user input for the delete command. Returns a list of parsed user input containing the
     * filter string and the index.
     *
     * @param userInput The user input string.
     * @return The filter string, set to either 'gym', 'run', 'bmi' or 'period'.
     */
    public static String[] parseDeleteInput(String userInput) throws CustomExceptions.InvalidInput {
        String[] parsedInputs = new String[2];
        try {
            String[] inputs = userInput.split(UiConstant.SPLIT_BY_SLASH);
            if (inputs.length != 3) {
                throw new CustomExceptions.InsufficientInput("Invalid command format." +
                        System.lineSeparator() +
                        "Usage: delete /item:filter /index:index");
            }

            String[] itemSplit = inputs[1].split(UiConstant.SPLIT_BY_COLON);
            if (itemSplit.length != 2 || !itemSplit[0].equalsIgnoreCase("item")) {
                throw new CustomExceptions.InvalidInput("No item specified." +
                        System.lineSeparator() +
                        "Use /item:run/gym/period/bmi");
            }

            validateFilter(itemSplit[1].trim());
            String[] indexSplit = inputs[2].split(UiConstant.SPLIT_BY_COLON);
            if (indexSplit.length != 2 || !indexSplit[0].equalsIgnoreCase("index")) {
                throw new CustomExceptions.InvalidInput("No index specified");
            }

            Integer.parseInt(indexSplit[1].trim());
            parsedInputs[1] = indexSplit[1].trim();
            return parsedInputs;

        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            Output.printException(e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            throw new CustomExceptions.InvalidInput("Index must be a valid positive integer.");
        }
    }

    /**
     * Validates whether the filter string is either 'run', 'gym', 'bmi' or 'period'.
     *
     * @param filter The filter string to be checked.
     * @throws CustomExceptions.InvalidInput If the filter string is none of them.
     */
    public static void validateFilter (String filter) throws CustomExceptions.InvalidInput {
        if (filter.equals(WorkoutConstant.RUN) || filter.equals(WorkoutConstant.GYM) ||
                filter.equals(HealthConstant.BMI) || filter.equals(HealthConstant.PERIOD)) {
            return;
        }
        throw new CustomExceptions.InvalidInput("Invalid item specified." +
                System.lineSeparator() +
                "/item:run/gym/bmi/period");
    }

    //@@author JustinSoh
    /**
     * Function validates and parses the user input for the history and latest commands.
     *
     * @param userInput String representing the user input.
     * @return The filter string, set to either 'gym', 'run', 'bmi' or 'period'.
     */
    public static String parseHistoryAndLatestInput(String userInput) {
        try {
            String[] inputs = userInput.split(UiConstant.SPLIT_BY_SLASH);
            if (inputs.length != 2) {
                throw new CustomExceptions.InsufficientInput("Invalid command format. " +
                        System.lineSeparator() +
                        "Usage: history/latest /view:filter");
            }
            String[] filterSplit = inputs[1].split(UiConstant.SPLIT_BY_COLON);
            if (filterSplit.length != 2 || !filterSplit[0].equalsIgnoreCase("item")) {
                throw new CustomExceptions.InvalidInput("No filter used!" +
                        System.lineSeparator() +
                        "Use /view:run/gym/period/bmi");
            }
            validateFilter(filterSplit[1].toLowerCase());
            return filterSplit[1].toLowerCase();
        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            Output.printException(e.getMessage());
            return null;
        }
    }
}
