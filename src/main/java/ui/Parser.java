package ui;

import command.CommandType;
import exception.CommandInputException;
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
            throw new NameInputException("please enter a valid name, try again!\n");
        }
        throw new NameInputException(
                "Oops! Your name is too long! Please enter a name with less than 15 characters.\n");
    }

    public static String parseCareer(String input) throws JobSelectException {
        switch (input.toLowerCase().trim()) {
        case "/r":
            ResponseManager.echoChosenIndustry("Robotics");
            return "Robotics";

        case "/s":
            ResponseManager.echoChosenIndustry("Semi-conductor");
            return "semi-conductor";

        case "/a":
            ResponseManager.echoChosenIndustry("Artificial Intelligence");
            return "artificial intelligence";

        default:
            throw new JobSelectException("Please enter a valid job type, try again!\n");
        }
    }

    public static CommandType parseCommand(String input) throws CommandInputException {
        return CommandType.analyseInput(input.trim().toLowerCase());
    }
}
