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
}
