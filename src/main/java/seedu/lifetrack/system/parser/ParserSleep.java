package seedu.lifetrack.system.parser;

import seedu.lifetrack.system.exceptions.InvalidInputException;

import static seedu.lifetrack.system.exceptions.ErrorMessages.getIncorrectSleepDateInputMessage;
import static seedu.lifetrack.system.exceptions.ErrorMessages.getIncorrectSleepInputMessage;

import seedu.lifetrack.sleep.sleeplist.SleepEntry;

public class ParserSleep {
    public static SleepEntry parseSleepInput(String input) throws InvalidInputException {
        try {
            String date = "N/A"; // Default if no date is provided
            double duration = 0;
            String[] parts = input.split(" ");
            for (String part : parts) {
                if (part.matches("^-?\\d+(\\.\\d+)?$")) {
                    duration = Double.parseDouble(part);
                    if (duration < 0) {
                        throw new InvalidInputException(getIncorrectSleepInputMessage());
                    }
                } else if (part.startsWith("d/")) {
                    date = part.substring(2);
                    if (!date.matches("\\d{6}")) {
                        throw new InvalidInputException(getIncorrectSleepDateInputMessage());
                    }
                }
            }
            if (duration == 0) {
                throw new InvalidInputException("Please ensure that you have keyed in the correct format: " +
                        "sleep add <duration> d/<date>");
            }
            return new SleepEntry(duration, date);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please ensure that you have keyed in the correct format: " +
                    "sleep add <duration> d/<date>");
        }
    }

}
