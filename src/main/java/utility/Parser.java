package utility;

import health.Appointment;
import health.Bmi;
import health.HealthList;
import health.Period;
import ui.Handler;
import ui.Output;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static health.HealthList.showPeriodHistory;

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
    public static void validateDateInput(String date) throws CustomExceptions.InvalidInput {
        String validDateRegex = "\\d{2}-\\d{2}-\\d{4}";
        if (!date.matches(validDateRegex)) {
            throw new CustomExceptions.InvalidInput("Invalid date format. Format is DD-MM-YYYY in integers.");
        }
        String[] parts = date.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        if (day < 1 || day > 31) {
            throw new CustomExceptions.InvalidInput("Day must be an integer between 01 and 31.");
        }

        if (month < 1 || month > 12) {
            throw new CustomExceptions.InvalidInput("Month must be an integer between 01 and 12.");
        }
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

    /**
     * Parses input for Bmi command. Adds Bmi object to HealthList if valid.
     *
     * @param userInput The user input string.
     */
    public static void parseBmiInput(String userInput) throws CustomExceptions.InvalidInput {
        String[] bmiDetails = splitBmiInput(userInput);
        validateBmiInput(bmiDetails);
        Bmi newBmi = new Bmi(bmiDetails[1], bmiDetails[2], bmiDetails[3]);
        HealthList.addBmi(newBmi);
        Output.printAddBmi(newBmi);
    }

    /**
     * Validates Bmi details entered.
     *
     * @param bmiDetails List of strings representing BMI details.
     * @throws CustomExceptions.InvalidInput If there are any errors in the details entered.
     */
    public static void validateBmiInput(String[] bmiDetails) throws CustomExceptions.InvalidInput {
        if (bmiDetails[1].isEmpty()
                || bmiDetails[2].isEmpty()
                || bmiDetails[3].isEmpty()) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INSUFFICIENT_BMI_PARAMETERS_ERROR);
        }
        // checks whether input number is 2dp
        String twoDecimalPlaceRegex = "\\d+\\.\\d{2}";
        if (!bmiDetails[1].matches(twoDecimalPlaceRegex) ||
                !bmiDetails[2].matches(twoDecimalPlaceRegex)) {
            throw new CustomExceptions.InvalidInput("Height and weight should be 2 decimal place positive numbers!");
        }
        validateDateInput(bmiDetails[3]);
    }

    //@@author syj02
    /**
     * Split user input into Bmi command, height, weight and date.
     *
     * @param input A user-provided string.
     * @return An array of strings containing the extracted Bmi parameters.
     * @throws CustomExceptions.InvalidInput If the user input is invalid or blank.
     */
    public static String[] splitBmiInput(String input) throws CustomExceptions.InvalidInput {
        String [] results = new String[HealthConstant.NUM_BMI_PARAMETERS];
        if (!input.contains(HealthConstant.HEALTH_FLAG)
                || !input.contains(HealthConstant.HEIGHT_FLAG)
                || !input.contains(HealthConstant.WEIGHT_FLAG)
                || !input.contains(HealthConstant.DATE_FLAG)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INSUFFICIENT_BMI_PARAMETERS_ERROR);
        }
        results[0] = Handler.extractSubstringFromSpecificIndex(input, HealthConstant.HEALTH_FLAG);
        results[1] = Handler.extractSubstringFromSpecificIndex(input, HealthConstant.HEIGHT_FLAG);
        results[2] = Handler.extractSubstringFromSpecificIndex(input, HealthConstant.WEIGHT_FLAG);
        results[3] = Handler.extractSubstringFromSpecificIndex(input, HealthConstant.DATE_FLAG);
        return results;
    }
    //@@author

    /**
     * Parses input for Period command. Adds Period object to HealthList if valid.
     *
     * @param userInput The user input string.
     */
    public static void parsePeriodInput(String userInput) throws CustomExceptions.InvalidInput {
        String[] periodDetails = splitPeriodInput(userInput);
        validatePeriodInput(periodDetails);
        Period newPeriod = new Period(periodDetails[1], periodDetails[2]);
        HealthList.addPeriod(newPeriod);
        Output.printAddPeriod(newPeriod);
    }

    /**
     * Split user input into Period command, start date and end date.
     *
     * @param input A user-provided string.
     * @return An array of strings containing the extracted Period parameters.
     * @throws CustomExceptions.InvalidInput If the user input is invalid or blank.
     */
    public static String[] splitPeriodInput(String input) throws CustomExceptions.InvalidInput {
        String [] results = new String[HealthConstant.PERIOD_PARAMETERS];

        if (!input.contains(HealthConstant.HEALTH_FLAG)
                | !input.contains(HealthConstant.START_FLAG)
                || !input.contains(HealthConstant.END_FLAG)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INSUFFICIENT_PERIOD_PARAMETERS_ERROR);
        }
        results[0] = Handler.extractSubstringFromSpecificIndex(input, HealthConstant.HEALTH_FLAG);
        results[1] = Handler.extractSubstringFromSpecificIndex(input, HealthConstant.START_FLAG);
        results[2] = Handler.extractSubstringFromSpecificIndex(input, HealthConstant.END_FLAG);
        return results;
    }

    /**
     * Validates Period details entered.
     *
     * @param periodDetails List of strings representing Period details.
     * @throws CustomExceptions.InvalidInput If there are any errors in the details entered.
     */
    public static void validatePeriodInput(String[] periodDetails) throws CustomExceptions.InvalidInput {
        if (periodDetails[1].isEmpty() || periodDetails[2].isEmpty()) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INSUFFICIENT_PERIOD_PARAMETERS_ERROR);
        }

        try {
            validateDateInput(periodDetails[1]);
        } catch (CustomExceptions.InvalidInput e) {
            throw new CustomExceptions.InvalidInput("Invalid start date!" +
                    System.lineSeparator() +
                    e.getMessage());
        }
        try {
            validateDateInput(periodDetails[2]);
        } catch (CustomExceptions.InvalidInput e) {
            throw new CustomExceptions.InvalidInput("Invalid end date!" +
                    System.lineSeparator() +
                    e.getMessage());
        }

        LocalDate startDate = parseDate(periodDetails[1]);
        LocalDate endDate = parseDate(periodDetails[2]);
        if (startDate.isAfter(endDate)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.PERIOD_END_BEFORE_START_ERROR);
        }
    }

    /**
     * Parses input for Prediction command.
     * Prints period prediction if possible.
     *
     * @throws CustomExceptions.InsufficientInput If prediction cannot be made.
     */
    public static void parsePredictionInput() throws CustomExceptions.InsufficientInput {
        showPeriodHistory();
        if (HealthList.getPeriodSize() >= HealthConstant.MINIMUM_SIZE_FOR_PREDICTION) {
            LocalDate nextPeriodStartDate = HealthList.predictNextPeriodStartDate();
            Period.printNextCyclePrediction(nextPeriodStartDate);
        } else {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.UNABLE_TO_MAKE_PREDICTIONS_ERROR);
        }
    }

    public static String[] getTimeParts(String time) throws CustomExceptions.InvalidInput {
        String [] parts;
        try {
            parts = time.split(":");
        } catch (Exception e) {
            throw new CustomExceptions.InvalidInput("Invalid delimiter. Use ':'");
        }
        if (parts.length != 2 && parts.length != 3) {
            throw new CustomExceptions.InvalidInput("Invalid time format! "
                    + System.lineSeparator()
                    + "Format is  either HH:MM, HH:MM:SS or MM:SS!");
        }

        return parts;
    }

    /**
     * Validates the time used in HH:MM format.
     *
     * @param time String representing the time to check.
     * @throws CustomExceptions.InvalidInput If time is formatted wrongly.
     */
    public static void validateTimeInput(String time) throws CustomExceptions.InvalidInput {
        String validTimeRegex = "\\d{2}:\\d{2}";
        if (!time.matches(validTimeRegex)) {
            throw new CustomExceptions.InvalidInput("Invalid time format. Format is HH:MM with integers");
        }
        String [] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        if (hours < 0 || hours > 23) {
            throw new CustomExceptions.InvalidInput("Hours must be a positive integer between 1 and 23");
        }
        if (minutes < 0 || minutes > 59) {
            throw new CustomExceptions.InvalidInput("Minutes must be a positive integer between 1 and 59");
        }
    }

    /**
     * Validates the time used in HH:MM format.
     *
     * @param time String representing the time to check.
     * @throws CustomExceptions.InvalidInput If time is formatted wrongly.
     */
    public static void validateRunTimeInput(String time) throws CustomExceptions.InvalidInput {
        String validTimeRegexWithHours = "\\d{2}:\\d{2}:\\d{2}";
        String validTimeRegex = "\\d{2}:\\d{2}";
        if (!time.matches(validTimeRegex) &&
                !time.matches(validTimeRegexWithHours)) {
            throw new CustomExceptions.InvalidInput("Invalid time format. " +
                    "Format is HH:MM:SS or MM:SS with integers");
        }
        String [] parts = time.split(":");
        int hours = -1; // if not needed, leave as -1.
        int minutes;
        int seconds;

        if (parts.length == 2) {
            minutes = Integer.parseInt(parts[0]);
            seconds = Integer.parseInt(parts[1]);
        } else if (parts.length == 3) {
            hours = Integer.parseInt(parts[0]);
            minutes = Integer.parseInt(parts[1]);
            seconds = Integer.parseInt(parts[2]);
        } else {
            throw new CustomExceptions.InvalidInput("Invalid time format. Format is HH:MM:SS or MM:SS with integers");
        }
        if (minutes < 1 || minutes > 60) {
            throw new CustomExceptions.InvalidInput("Minutes must be a positive integer between 01 and 59.");
        }

        if (seconds < 1 || seconds > 60) {
            throw new CustomExceptions.InvalidInput("Minutes must be a positive integer between 01 and 59.");
        }

        if (hours == 0) {
            throw new CustomExceptions.InvalidInput("Hours cannot be 0. Use MM:SS instead");
        }
    }

    /**
     * Validates Appointment details entered.
     *
     * @param appointmentDetails List of strings representing Appointment details.
     * @throws CustomExceptions.InvalidInput If there are any errors in the details entered.
     */
    public static void validateAppointmentDetails(String[] appointmentDetails)
            throws CustomExceptions.InvalidInput {
        if (appointmentDetails[1].isEmpty()
                || appointmentDetails[2].isEmpty()
                || appointmentDetails[3].isEmpty()) {
            throw new CustomExceptions.InvalidInput(ErrorConstant
                    .INSUFFICIENT_APPOINTMENT_PARAMETERS_ERROR);
        }
        validateDateInput(appointmentDetails[1]);
        validateTimeInput(appointmentDetails[2]);

        if (appointmentDetails[3].length() > 100) {
            throw new CustomExceptions.InvalidInput("Description cannot be more than 100 characters");
        }
    }

    /**
     * Split user input into Appointment command, date, time and description.
     *
     * @param input A user-provided string.
     * @return An array of strings containing the extracted Appointment parameters.
     * @throws CustomExceptions.InvalidInput If the user input is invalid or blank.
     */
    public static String[] splitAppointmentDetails(String input)
            throws CustomExceptions.InvalidInput {
        String [] results = new String[HealthConstant.APPOINTMENT_PARAMETERS];
        if (!input.contains(HealthConstant.HEALTH_FLAG)
                || !input.contains(HealthConstant.DATE_FLAG)
                || !input.contains(HealthConstant.TIME_FLAG)
                || !input.contains(HealthConstant.DESCRIPTION_FLAG)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INSUFFICIENT_APPOINTMENT_PARAMETERS_ERROR);
        }
        results[0] = Handler.extractSubstringFromSpecificIndex(input, HealthConstant.HEALTH_FLAG);
        results[1] = Handler.extractSubstringFromSpecificIndex(input, HealthConstant.DATE_FLAG);
        results[2] = Handler.extractSubstringFromSpecificIndex(input, HealthConstant.TIME_FLAG);
        results[3] = Handler.extractSubstringFromSpecificIndex(input, HealthConstant.DESCRIPTION_FLAG);
        return results;
    }

    /**
     * Parses input for Appointment command. Adds Appointment object to HealthList if valid.
     *
     * @param userInput The user input string.
     */
    public static void parseAppointmentInput(String userInput) throws CustomExceptions.InvalidInput {
        String[] appointmentDetails = splitAppointmentDetails(userInput);
        validateAppointmentDetails(appointmentDetails);
        if (appointmentDetails[0].isEmpty()
                || appointmentDetails[1].isEmpty()
                || appointmentDetails[2].isEmpty()
                ||  appointmentDetails[3].isEmpty()) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.UNSPECIFIED_PARAMETER_ERROR);
        }

        Appointment newAppointment = new Appointment(appointmentDetails[1],
                appointmentDetails[2],
                appointmentDetails[3]);
        HealthList.addAppointment(newAppointment);
        Output.printAddAppointment(newAppointment);
    }
}
