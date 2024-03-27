package brokeculator.parser;

import brokeculator.command.AddEventCommand;
import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;

public class EventParser {
    private static final String[] EVENT_KEYWORDS = {" /n ", " /d "};
    private static final String[] KEYWORDS_MEANINGS = {"Event name", "Event description"};
    public static Command parseInput(String userInput) {

        int[] keywordIndexes = new int[EVENT_KEYWORDS.length];
        for (int i = 0; i < EVENT_KEYWORDS.length; i++) {
            keywordIndexes[i] = userInput.indexOf(EVENT_KEYWORDS[i]);
        }

        for (int i = 0; i < keywordIndexes.length; i++) {
            if (keywordIndexes[i] == -1) {
                return new InvalidCommand(KEYWORDS_MEANINGS[i] + " is missing");
            }
        }

        for (int i = 0; i < keywordIndexes.length - 1; i++) {
            if (keywordIndexes[i] > keywordIndexes[i + 1]) {
                return new InvalidCommand(KEYWORDS_MEANINGS[i] + " should appear before " + KEYWORDS_MEANINGS[i + 1]);
            }
        }

        String[] userInputs = new String[EVENT_KEYWORDS.length];
        for (int i = 0; i < EVENT_KEYWORDS.length; i++) {
            int startIndex = keywordIndexes[i] + EVENT_KEYWORDS[i].length();
            int endIndex = i == EVENT_KEYWORDS.length - 1 ? userInput.length() : keywordIndexes[i + 1];
            if (startIndex >= endIndex) {
                return new InvalidCommand(KEYWORDS_MEANINGS[i] + " is missing");
            }
            userInputs[i] = userInput.substring(startIndex, endIndex).trim();
            if (userInputs[i].isBlank()) {
                return new InvalidCommand(KEYWORDS_MEANINGS[i] + " cannot be empty");
            }
        }

        return new AddEventCommand(userInputs[0], userInputs[1]);
    }
}
