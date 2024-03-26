package ui;

import java.util.logging.Logger;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Parser {
    private static final Logger logger = Logger.getLogger(Parser.class.getName());

    /**
     * Analyzes the given input and returns the corresponding token.
     *
     * @param input The input to be analyzed.
     * @return The corresponding token.
     * @throws IllegalArgumentException If the input is invalid.
     */
    public static CommandType analyzeInput(String input) throws IllegalArgumentException {
        logger.entering(Parser.class.getName(), "analyzeInput", input);

        return Arrays.stream(CommandType.values())
                .filter(token -> input.matches(token.getCommandRegex()))
                .findFirst()
                .orElseThrow(() -> {
                    logger.throwing(Parser.class.getName(),
                            "analyzeInput", new IllegalArgumentException("Invalid input"));
                    return new IllegalArgumentException("Invalid input");
                });
    }

    /**
     * Splits the given input based on the given token.
     *
     * @param token The token to be used for splitting the input.
     * @param input The input to be split.
     * @return The split input containing the arguments required for the command.
     */
    public static String[] splitInput(CommandType token, String input) {
        logger.entering(Parser.class.getName(),
                "splitInput", new Object[]{token, input});

        assert token != null : "Token cannot be null";  // Ensures command type token is not null

        Pattern matchedPattern = Pattern.compile(token.getCommandRegex());
        Matcher matcher = matchedPattern.matcher(input);
        matcher.matches();

        return IntStream.rangeClosed(1, matcher.groupCount())
                .mapToObj(i -> matcher.group(i).trim())
                .filter(s -> !s.isEmpty())
                .peek(s -> logger.fine("Split input: " + s))
                .toArray(String[]::new);
    }
}
