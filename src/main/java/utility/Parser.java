package utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the parser used for PulsePilot
 */
public class Parser {

    /**
     * Parses and converts String date to a LocalDate variable.
     * @param dateTime String representing the date.
     * @return LocalDate variable representing the date.
     *
     * @throws DateTimeParseException If there is an error parsing the date.
     */
    public static LocalDate parseDate(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate formattedDate = null;
        try {
            formattedDate = LocalDate.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }
        return formattedDate;
    }

    /**
     * Validates that the input date string is correctly formatted in DD-MM-YYYY.
     * @param date The string date from user input.
     * @throws CustomExceptions.InvalidInput If there are invalid date strings inputted.
     */
    public static void checkDateInput(String date) throws CustomExceptions.InvalidInput {
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
}
