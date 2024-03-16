package ui;

import exception.JobSelectException;
import exception.NameInputException;

public class Parser {
    private static final String NAME = "^[a-zA-Z ]{1,15}$";
    private static final int NAME_LENGTH_LIMIT = 15;

    public static String parseName(String input) throws NameInputException {
        if (input.matches(NAME)) {
            return input;
        }
        if (input.length() <= NAME_LENGTH_LIMIT) {
            throw new NameInputException("please enter a valid name, try again!");
        }
        throw new NameInputException(
                "Oops! Your name is too long! Please enter a name with less than 15 characters.");
    }

    public static String parseCareer(String input) throws JobSelectException {
        if (input.equals("Robotics") ||
                input.equals("Semiconductor industry") ||
                input.equals("Artificial intelligence")) {
            ResponseManager.echoChosenIndustry(input);
            return input;
        }
        throw new JobSelectException("Please enter a valid job type, try again!");
    }
}
