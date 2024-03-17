package seedu.stockpal.common;

public class Messages {
    public static final String HORIZONTAL_LINE = "==================================================";
    public static final String MESSAGE_WELCOME = "Welcome to StockPal!";
    public static final String MESSAGE_GOODBYE = "Exiting program, goodbye!";

    public static final String MESSAGE_ADDED = "Product added!";
    public static final String MESSAGE_REFER_TO_HELP = "For more information on the commands, use `help`.";
    public static final String MESSAGE_INVALID_COMMAND = "Invalid command. " + MESSAGE_REFER_TO_HELP;

    public static final String ERROR_MESSAGE_GENERAL = "OOPS!!! Error Occurred: ";
    public static final String WARNING_INVALID_FILEPATH = "OOPS!!! Storage file should end with '.csv'";
    public static final String WARNING_DATA_ERROR = "OOPS!!! Data file contains erroneous input!";
    public static final String WARNING_READ_DATA_ERROR = "OOPS!!! Error reading data file";
    public static final String WARNING_DATA_FILE_FORMAT_ERROR = "OOPS!! Error in data file format!";
    public static final String WARNING_CLOSE_WRITER_ERROR = "OOPS!! Error in closing save file writer!";
    public static final String MESSAGE_FILE_ALR_CREATED = "A non-existent file scenario is already handled earlier.";
}
