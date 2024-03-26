package brokeculator.parser;

import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;
import brokeculator.command.SummariseCommand;

import java.time.LocalDateTime;

public class SummariseParser {
    private static final String[] SUMMARISE_COMMAND_OPTIONS = {" /n ", " /d ", " /c ", " /from ", " /to "};
    private static final int NAME_INDEX = 0;
    private static final int DATE_INDEX = 1;
    private static final int CATEGORY_INDEX = 2;
    private static final int FROM_INDEX = 3;
    private static final int TO_INDEX = 4;

    public static Command parseInput(String userInput) {
        String[] userInputAsArray = userInput.trim().split("\\s+");
        String nameToSummariseBy = null;
        LocalDateTime dateToSummariseBy = null;
        String categoryToSummariseBy = null;
        int beginIndex = 0;
        int endIndex = -1;

        if (userInput.contains(SUMMARISE_COMMAND_OPTIONS[NAME_INDEX])) {
            nameToSummariseBy = getOptionField(userInputAsArray, SUMMARISE_COMMAND_OPTIONS[NAME_INDEX]);
            nameToSummariseBy = nameToSummariseBy.isBlank() ? null : nameToSummariseBy;
        }

        // TODO implement date processing

        // TODO implement category processing

        if (userInput.contains(SUMMARISE_COMMAND_OPTIONS[FROM_INDEX])) {
            try {
                String beginIndexAsString = getOptionField(userInputAsArray, SUMMARISE_COMMAND_OPTIONS[FROM_INDEX]);
                beginIndexAsString = beginIndexAsString.isBlank() ? "0" : beginIndexAsString;
                beginIndex = Integer.parseInt(beginIndexAsString.trim()) - 1;
            } catch (NumberFormatException e) {
                return new InvalidCommand("Start index cannot be non-integer");
            }
            if (beginIndex < 0) {
                return new InvalidCommand("Start index must be one or greater");
            }
        }

        assert beginIndex >= 0 : "beginIndex should be 0 or greater";

        if (userInput.contains(SUMMARISE_COMMAND_OPTIONS[TO_INDEX])) {
            try {
                String endIndexAsString = getOptionField(userInputAsArray, SUMMARISE_COMMAND_OPTIONS[TO_INDEX]);
                endIndexAsString = endIndexAsString.isBlank() ? "0" : endIndexAsString;
                endIndex = Integer.parseInt(endIndexAsString.trim()) - 1;
            } catch (NumberFormatException e) {
                return new InvalidCommand("End index cannot be non-integer");
            }
            if (endIndex < 0) {
                return new InvalidCommand("End index must be one or greater");
            }
        }

        assert endIndex >= -1 : "endIndex should be -1 or greater";

        if (endIndex != -1 && beginIndex > endIndex) {
            return new InvalidCommand("Start index cannot be greater than end index");
        }

        return new SummariseCommand(nameToSummariseBy, dateToSummariseBy, categoryToSummariseBy,
                beginIndex, endIndex);
        
    }

    private static String getOptionField(String[] userInputArray, String option) {
        StringBuilder optionField = new StringBuilder();
        boolean startAppending = false;
        for (String s : userInputArray) {
            if (s.equals(option.trim())) {
                startAppending = true;
                continue;
            }
            if (isWordOption(s) && startAppending) {
                return optionField.toString().trim();
            }
            if (startAppending) {
                optionField.append(s).append(" ");
            }
        }
        return optionField.toString().trim();
    }

    private static boolean isWordOption(String word) {
        for (String option : SUMMARISE_COMMAND_OPTIONS) {
            if (word.equals(option.trim())) {
                return true;
            }
        }
        return false;
    }

}
