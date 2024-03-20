package seedu.duke;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        Pattern pattern = Pattern.compile(regex);
        // Create a matcher object to match the input against the pattern
        Matcher matcher = pattern.matcher(input);

        if (!input.matches(regex)) {
            throw new InvalidFormatException("[ERROR] Invalid compare format. Expected format: compare <user1> <user2>.");
        }
    }
}