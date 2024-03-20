package seedu.duke;

public class InputValidator {
    /**
     * Validates correctly formatted compare command. The expected format is
     * "compare user1 user2"
     *
     * @param input String Input.
     * @throws InvalidFormatException If the input does not match the expected format.
     */
    public static void validateCompareInput(String input) throws InvalidFormatException {
        // Define the regex pattern for the expected format with case-insensitive flag
        String regex = "(?i)^compare\\s+\\w+\\s+\\w+$";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid compare format. " +
                    "Expected format: compare <user1> <user2>");
        }
    }

    /**
     * Validates correctly formatted addTask command. The expected format is
     * "addtask /on [date] /task [description] /from [start time] /to [start time]" format
     * Note: Start and End times should be formatted as such: HH:mm
     *
     * @param input String Input.
     * @throws InvalidFormatException If the input does not match the expected format.
     */
    public static void validateAddTaskInput(String input) throws InvalidFormatException {
        // Define the regex pattern for the expected format with case-insensitive flag
        String regex = "(?i)^addtask\\s+/on\\s+(\\w+)\\s+/task\\s+(.+?)\\s" +
                "+/from\\s+(\\d{1,2}:\\d{2})\\s+/to\\s+(\\d{1,2}:\\d{2})$";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid addTask format. " +
                    "Expected format: addTask /on [day] /task [description] /from [start time] /to [start time]");
        }
    }

    /**
     * Validates correctly formatted deleteTask command. The expected format is
     * "deletetask /on [day] /index [index]" format
     * Note: index is the index of the task in the task list for the given day
     *
     * @param input String Input.
     * @throws InvalidFormatException If the input does not match the expected format.
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
     * Validates correctly formatted addUser command. The expected format is "adduser user"
     *
     * @param input String Input.
     * @throws InvalidFormatException If the input does not match the expected format.
     */
    public static void validateAddUserInput(String input) throws InvalidFormatException {
        // Define the regex pattern for the expected format with case-insensitive flag
        String regex = "(?i)^adduser\\s+\\w+$";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid addUser format. " +
                    "Expected format: addUser <desired user's name>");
        }
    }

    /**
     * Validates correctly formatted switch command. The expected format is "switch user"
     *
     * @param input String Input.
     * @throws InvalidFormatException If the input does not match the expected format.
     */
    public static void validateSwitchInput(String input) throws InvalidFormatException {
        // Define the regex pattern for the expected format with case-insensitive flag
        String regex = "(?i)^switch\\s+\\w+$";

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid switch format. " +
                    "Expected format: switch <desired user's name>");
        }
    }

    /**
     * Validates correctly spelled usernames that currently exist in the user database.
     *
     * @param input String Input.
     * @throws InvalidUserException If the input does not match any current user's name in the user database.
     */
    public static void validateUserInput(String input, UserList userList) throws InvalidUserException {
        if (userList.getUsers().isEmpty()) {
            throw new InvalidUserException("[ERROR] Current User List is empty. Please add users.");
        }
        for (User u : userList.getUsers()) {
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
     * @throws InvalidDayException If the input is not an actual day.
     */
    public static void validateDay(String input) throws InvalidDayException {
        String[] validDays = new String[]{"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

        for (String day : validDays) {
            if (day.equalsIgnoreCase(input)) {
                return;
            }
        }
        throw new InvalidDayException("[ERROR] Invalid day. Please enter a day from Monday - Sunday.");
    }

    /**
     * Validates whether a Timetable exist or not.
     *
     * @param table inputted Timetable.
     * @throws NullPointerException If the input Timetable does not exist.
     */
    public static void validateTableExistence(Timetable table) throws NullPointerException {
        if (table == null) {
            throw new NullPointerException("Timetable object is null.");
        }
    }
}
