package utility;

import ui.Output;

import java.time.LocalDate;
import java.time.LocalTime;
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
     * Parses and converts String time to a LocalDate variable.
     * @param stringTime String representing the time.
     * @return LocalTime variable representing the time.
     *
     * @throws DateTimeParseException If there is an error parsing the time.
     */
    public static LocalTime parseTime(String stringTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime formattedTime = null;
        try {
            formattedTime = LocalTime.parse(stringTime, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing time: " + e.getMessage());
        }
        return formattedTime;
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
}
