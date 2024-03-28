package utility;

import ui.Output;

import health.Appointment;
import health.Bmi;
import health.HealthList;
import health.Period;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate formattedDate = null;
        try {
            formattedDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            Output.printException(ErrorConstant.PARSING_DATE_ERROR);
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
            Output.printException(ErrorConstant.PARSING_TIME_ERROR);
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
        if (!date.matches(UiConstant.VALID_DATE_REGEX)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_DATE_ERROR);
        }
        String[] parts = date.split(UiConstant.DASH);
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        if (day < UiConstant.MIN_DAY || day > UiConstant.MAX_DAY) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_DAY_ERROR);
        }

        if (month < UiConstant.MIN_MONTH || month > UiConstant.MAX_MONTH) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_MONTH_ERROR);
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
                throw new CustomExceptions.InsufficientInput(ErrorConstant.INVALID_COMMAND_FORMAT_ERROR
                        + System.lineSeparator()
                        + ErrorConstant.CORRECT_DELETE_COMMAND_FORMAT);
            }

            String[] itemSplit = inputs[1].split(UiConstant.SPLIT_BY_COLON);
            if (itemSplit.length != 2 || !itemSplit[0].equalsIgnoreCase("item")) {
                throw new CustomExceptions.InvalidInput(ErrorConstant.NULL_ITEM_ERROR
                        + System.lineSeparator()
                        + ErrorConstant.CORRECT_ITEM_FORMAT);
            }

            validateFilter(itemSplit[1].trim());
            String[] indexSplit = inputs[2].split(UiConstant.SPLIT_BY_COLON);
            if (indexSplit.length != 2 || !indexSplit[0].equalsIgnoreCase("index")) {
                throw new CustomExceptions.InvalidInput(ErrorConstant.NULL_INDEX_ERROR);
            }

            Integer.parseInt(indexSplit[1].trim());
            parsedInputs[1] = indexSplit[1].trim();
            return parsedInputs;

        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            Output.printException(e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.NEGATIVE_INDEX_ERROR);
        }
    }

    /**
     * Validates whether the filter string is either 'run', 'gym', 'bmi', 'period' 
     * or 'appointment'. 
     * 
     * @param filter The filter string to be checked.
     * @throws CustomExceptions.InvalidInput If the filter string is none of them.
     */
    public static void validateFilter (String filter) throws CustomExceptions.InvalidInput {
        if (filter.equals(WorkoutConstant.RUN) 
                || filter.equals(WorkoutConstant.GYM) 
                || filter.equals(HealthConstant.BMI) 
                || filter.equals(HealthConstant.PERIOD)
                || filter.equals(HealthConstant.APPOINTMENT)
                || filter.equals(WorkoutConstant.ALL)) {
            return;
        }
        throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_ITEM_ERROR
                + System.lineSeparator()
                + ErrorConstant.CORRECT_FILTER_ITEM_FORMAT);
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
            String type = extractSubstringFromSpecificIndex(userInput, UiConstant.ITEM_FLAG);

            if (type.isBlank()) {
                throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_HISTORY_FILTER_ERROR);
            }
            validateFilter(type.toLowerCase());
            return type.toLowerCase();
        } catch (CustomExceptions.InvalidInput e) {
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
        Bmi newBmi = new Bmi(bmiDetails[0], bmiDetails[1], bmiDetails[2]);
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
        if (bmiDetails[0].isEmpty()
                || bmiDetails[1].isEmpty()
                || bmiDetails[2].isEmpty()) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INSUFFICIENT_BMI_PARAMETERS_ERROR);
        }

        if (!bmiDetails[0].matches(UiConstant.VALID_TWO_DP_NUMBER_REGEX) ||
                !bmiDetails[1].matches(UiConstant.VALID_TWO_DP_NUMBER_REGEX)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.HEIGHT_WEIGHT_INPUT_ERROR);
        }
        validateDateInput(bmiDetails[2]);
        LocalDate date = parseDate(bmiDetails[2]);
        if (date.isAfter(LocalDate.now())) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.DATE_IN_FUTURE_ERROR);
        }

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
        if (!input.contains(HealthConstant.HEIGHT_FLAG)
                || !input.contains(HealthConstant.WEIGHT_FLAG)
                || !input.contains(HealthConstant.DATE_FLAG)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INSUFFICIENT_BMI_PARAMETERS_ERROR);
        }
        results[0] = extractSubstringFromSpecificIndex(input, HealthConstant.HEIGHT_FLAG);
        results[1] = extractSubstringFromSpecificIndex(input, HealthConstant.WEIGHT_FLAG);
        results[2] = extractSubstringFromSpecificIndex(input, HealthConstant.DATE_FLAG);
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
        Period newPeriod = new Period(periodDetails[0], periodDetails[1]);
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
        String [] results = new String[HealthConstant.NUM_PERIOD_PARAMETERS];

        if (!input.contains(HealthConstant.START_FLAG)
                || !input.contains(HealthConstant.END_FLAG)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INSUFFICIENT_PERIOD_PARAMETERS_ERROR);
        }
        results[0] = extractSubstringFromSpecificIndex(input, HealthConstant.START_FLAG);
        results[1] = extractSubstringFromSpecificIndex(input, HealthConstant.END_FLAG);
        return results;
    }

    /**
     * Validates Period details entered.
     *
     * @param periodDetails List of strings representing Period details.
     * @throws CustomExceptions.InvalidInput If there are any errors in the details entered.
     */
    public static void validatePeriodInput(String[] periodDetails) throws CustomExceptions.InvalidInput {
        if (periodDetails[0].isEmpty() || periodDetails[1].isEmpty()) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INSUFFICIENT_PERIOD_PARAMETERS_ERROR);
        }

        try {
            validateDateInput(periodDetails[0]);
        } catch (CustomExceptions.InvalidInput e) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_START_DATE_ERROR
                    + System.lineSeparator()
                    + e.getMessage());
        }
        try {
            validateDateInput(periodDetails[1]);
        } catch (CustomExceptions.InvalidInput e) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_END_DATE_ERROR
                    + System.lineSeparator()
                    + e.getMessage());
        }

        LocalDate startDate = parseDate(periodDetails[0]);
        LocalDate endDate = parseDate(periodDetails[1]);
        if (startDate.isAfter(LocalDate.now())) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.START_DATE_IN_FUTURE_ERROR);
        }
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
        if (HealthList.getPeriodSize() >= HealthConstant.MIN_SIZE_FOR_PREDICTION) {
            HealthList.printLatestThreeCycles();
            LocalDate nextPeriodStartDate = HealthList.predictNextPeriodStartDate();
            Period.printNextCyclePrediction(nextPeriodStartDate);
        } else {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.UNABLE_TO_MAKE_PREDICTIONS_ERROR);
        }
    }

    /**
     * Validates the time used in HH:MM format.
     *
     * @param time String representing the time to check.
     * @throws CustomExceptions.InvalidInput If time is formatted wrongly.
     */
    public static void validateTimeInput(String time) throws CustomExceptions.InvalidInput {
        if (!time.matches(UiConstant.VALID_TIME_REGEX)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_TIME_ERROR);
        }
        String [] parts = time.split(UiConstant.SPLIT_BY_COLON);
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        if (hours < UiConstant.MIN_HOURS || hours > UiConstant.MAX_HOURS) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_HOURS_ERROR);
        }
        if (minutes < UiConstant.MIN_MINUTES || minutes > UiConstant.MAX_MINUTES) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_MINUTES_ERROR);
        }
    }

    /**
     * Validates the time used in HH:MM format.
     *
     * @param time String representing the time to check.
     * @throws CustomExceptions.InvalidInput If time is formatted wrongly.
     */
    public static void validateRunTimeInput(String time) throws CustomExceptions.InvalidInput {
        if (!time.matches(UiConstant.VALID_TIME_REGEX) &&
                !time.matches(UiConstant.VALID_TIME_WITH_HOURS_REGEX)) {
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
        if (appointmentDetails[0].isEmpty()
                || appointmentDetails[1].isEmpty()
                || appointmentDetails[2].isEmpty()) {
            throw new CustomExceptions.InvalidInput(ErrorConstant
                    .INSUFFICIENT_APPOINTMENT_PARAMETERS_ERROR);
        }
        validateDateInput(appointmentDetails[0]);
        validateTimeInput(appointmentDetails[1]);

        if (appointmentDetails[2].length() > HealthConstant.MAX_DESCRIPTION_LENGTH) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.DESCRIPTION_LENGTH_ERROR);
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
        String [] results = new String[HealthConstant.NUM_APPOINTMENT_PARAMETERS];
        if (!input.contains(HealthConstant.DATE_FLAG)
                || !input.contains(HealthConstant.TIME_FLAG)
                || !input.contains(HealthConstant.DESCRIPTION_FLAG)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INSUFFICIENT_APPOINTMENT_PARAMETERS_ERROR);
        }
        results[0] = extractSubstringFromSpecificIndex(input, HealthConstant.DATE_FLAG);
        results[1] = extractSubstringFromSpecificIndex(input, HealthConstant.TIME_FLAG);
        results[2] = extractSubstringFromSpecificIndex(input, HealthConstant.DESCRIPTION_FLAG);
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
        Appointment newAppointment = new Appointment(appointmentDetails[0],
                appointmentDetails[1],
                appointmentDetails[2]);
        HealthList.addAppointment(newAppointment);
        Output.printAddAppointment(newAppointment);
    }

    /**
     * Extracts a substring from the given input string based on the provided delimiter.
     *
     * @param input     The input string from which to extract the substring.
     * @param delimiter The delimiter to search for in the input string.
     * @return The extracted substring, or an empty string if the delimiter is not found.
     */
    public static String extractSubstringFromSpecificIndex(String input, String delimiter) {
        int index = input.indexOf(delimiter);
        if (index == -1 || index == input.length() - delimiter.length()) {
            return "";
        }

        int startIndex = index + delimiter.length();
        int endIndex = input.indexOf("/", startIndex);
        if (endIndex == -1) {
            endIndex = input.length();
        }
        return input.substring(startIndex, endIndex).trim();
    }
}
