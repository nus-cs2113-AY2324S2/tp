package seedu.duke;

public class InputValidator {
    /**
     * Validates correctly formatted compare command.
     *
     * @param input String Input.
     * @throws InvalidFormatException if input does not match "compare <user1> <user2>" format
     */
    public static void validateCompareInput(String input) throws InvalidFormatException {
        // Define the regex pattern for the expected format with case-insensitive flag
        String regex = "(?i)^compare\\s+\\w+\\s+\\w+$";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid compare format. Expected format: compare <user1> <user2>");
        }
    }

    /**
     * Validates correctly formatted addTask command.
     *
     * @param input String Input.
     * @throws InvalidFormatException if input does not match "addTask /on [date] /task [description] /from [start time] /to [start time]" format
     * Note: Start and End times should be formatted as such: HH:mm
     */
    public static void validateAddTaskInput(String input) throws InvalidFormatException {
        // Define the regex pattern for the expected format with case-insensitive flag
        String regex = "(?i)^addtask\\s+/on\\s+(\\w+)\\s+/task\\s+(.+?)\\s+/from\\s+(\\d{2}:\\d{2})\\s+/to\\s+(\\d{2}:\\d{2})$";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid addTask format. " +
                    "Expected format: addTask /on [day] /task [description] /from [start time] /to [start time]");
        }
    }

    /**
     * Validates correctly formatted deleteTask command.
     *
     * @param input String Input.
     * @throws InvalidFormatException if input does not match "deleteTask /on [day] /index [index]" format
     * Note: index is the index of the task in the task list for the given day
     */
    public static void validateDeleteTaskInput(String input) throws InvalidFormatException {
        // Define the regex pattern for the expected format with case-insensitive flag
        String regex = "(?i)^deletetask\\s+/on\\s+(\\w+)\\s+/index\\s+(\\d+)$";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid deleteTask format. " +
                    "Expected format: deleteTask /on [day] /index [index]");
        }
    }

    /**
     * Validates correctly formatted addUser command.
     *
     * @param input String Input.
     * @throws InvalidFormatException if input does not match "addUser <user>" format
     */
    public static void validateAddUserInput(String input) throws InvalidFormatException {
        // Define the regex pattern for the expected format with case-insensitive flag
        String regex = "(?i)^adduser\\s+\\w+$";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid addUser format. Expected format: addUser <desired user's name>");
        }
    }

    /**
     * Validates correctly formatted switch command.
     *
     * @param input String Input.
     * @throws InvalidFormatException if input does not match "switch <user>" format
     */
    public static void validateSwitchInput(String input) throws InvalidFormatException {
        // Define the regex pattern for the expected format with case-insensitive flag
        String regex = "(?i)^switch\\s+\\w+$";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid switch format. Expected format: switch <desired user's name>");
        }
    }

    /**
     * Validates correctly spelled user names that currently exist in the user database.
     *
     * @param input String Input.
     * @throws InvalidUserException if input does not match any current user's name in the user database.
     */
    public static void validateUserInput(String input) throws InvalidUserException {
        if (UserList.GetUsers().isEmpty()) {
            throw new InvalidUserException("[ERROR] Current User List is empty. Please add users.");
        }
        for (User u : UserList.GetUsers()) {
            if (u.getName().toLowerCase().equals(input)) {
                return;
            }
        }
        throw new InvalidUserException("[ERROR] Invalid User: " + input + ". Please input a existing user name");
    }

    /**
     * Validates if the inputted string is an actual day.
     *
     * @param input String Input.
     * @throws InvalidDayException if the input is not an actual day.
     */
    public static void validateDay(String input) throws InvalidDayException {
        String[] validDays = new String[]{"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

        for (String day : validDays) {
            if (day.equals(input.toLowerCase())) {
                return;
            }
        }
        throw new InvalidDayException("[ERROR] Invalid day. Please enter a day from Monday - Sunday.");
    }

    /**
     * Validates whether a Timetable exist or not.
     *
     * @param table inputted Timetable.
     * @throws NullPointerException if input Timetable does not exist.
     */
    public static void validateTableExistence(Timetable table) throws NullPointerException {
        if (table == null) {
            throw new NullPointerException("Timetable object is null.");
        }
    }
}