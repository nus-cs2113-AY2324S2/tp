package seedu.duke;

import seedu.duke.ui.ResponseManager;

public class Parser {
    private static final String NAME = "^[a-zA-Z ]{1,15}$";

    public static String parseName(String input) {
        if (input.matches(NAME)) {
            return input;
        }
        if (input.length() <= 15) {
            ResponseManager.printInvalidNameMessage();
        } else {
            ResponseManager.printNameTooLongMessage();
        }
        return "";
    }

    public static String parseCareer(String input) {
        if (input.equals("Robotics") ||
                input.equals("Semiconductor industry") ||
                input.equals("Artificial intelligence")) {
            ResponseManager.printChooseIndustryMessage(input);
            return input;
        }
        ResponseManager.printJobSelectionErrorMessage();
        return "";
    }

}