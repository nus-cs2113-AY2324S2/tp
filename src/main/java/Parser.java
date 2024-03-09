import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Parser {
    /**
     * Analyzes the given input and returns the corresponding token.
     *
     * @param input The input to be analyzed.
     * @return The corresponding token.
     * @throws IllegalArgumentException If the input is invalid.
     */
    public static CommandType analyzeInput(String input) throws IllegalArgumentException {
        return Arrays.stream(CommandType.values())
                .filter(token -> input.matches(token.getCommandRegex()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid input"));
    }

    /**
     * Splits the given input based on the given token.
     *
     * @param token The token to be used for splitting the input.
     * @param input The input to be split.
     * @return The split input containing the arguments required for the command.
     */
    public static String[] splitInput(CommandType token, String input) {
        Pattern matchedPattern = Pattern.compile(token.getCommandRegex());
        Matcher matcher = matchedPattern.matcher(input);
        matcher.matches();

        return IntStream.rangeClosed(1, matcher.groupCount())
                .mapToObj(i -> matcher.group(i).trim())
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }

    // unit test for the parser
    public static void main(String[] args) {
        String[] inputs = {
            "TEST CASES FOR NORMAL OPERATION",
            "create order -menu 1",
            "view -order 2",
            "edit -order 3",
            "bye",
            "help",
            "asfdhih 123",
            "create order -menu 1 2 3",
            "view -order 1 2 3",
            "edit -order 1 2 3",
            "create order -menu",
            "view -order",
            "edit -order",

            "TEST CASES FOR CASE INSENSITIVITY",
            "CrEaTe OrDeR -mEnU 99",
            "ViEw -OrDeR 2",
            "EdIt -OrDeR 1",
            "ByE",
            "HeLp",
            "AsFdHiH 123",
            "CrEaTe OrDeR -mEnU 1 2 3"
        };

        for (String input : inputs) {
            try {
                CommandType token = analyzeInput(input);
                String[] arguments = splitInput(token, input);
                System.out.println("Input: " + input);
                System.out.println("Token: " + token);
                System.out.println("Arguments: " + Arrays.toString(arguments));
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + input);
                System.out.println();
            }
        }

    }
}
