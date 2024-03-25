package seedu.lifetrack.system.parser;

import seedu.lifetrack.sleep.Sleep;
import seedu.lifetrack.system.exceptions.InvalidInputException;

import static seedu.lifetrack.system.exceptions.ErrorMessages.getIncorrectSleepDateInputMessage;
import static seedu.lifetrack.system.exceptions.ErrorMessages.getIncorrectSleepInputMessage;

public class ParserSleep {
    public static Sleep parseSleepInput(String input) throws InvalidInputException {
        try {
            String date = "N/A"; // Default if no date is provided
            double duration = 0;
            String[] parts = input.split(" ");
            for (String part : parts) {
                if (part.startsWith("t/")) {
                    duration = Double.parseDouble(part.substring(2));
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
                        "sleep add t/<duration> d/<date>");
            }
            return new Sleep(date, duration);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please ensure that you have keyed in the correct format: " +
                    "sleep add t/<duration> d/<date>");
        }
    }

}
