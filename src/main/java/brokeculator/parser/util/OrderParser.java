package brokeculator.parser.util;

public class OrderParser {
    public static String[] parseOrder(String userInput, Keyword[] keywords)
            throws Exception {

        int[] keywordPositions = new int[keywords.length];
        for (int i = 0; i < keywords.length; i++) {
            keywordPositions[i] = userInput.indexOf(keywords[i].keywordMarker);
        }

        for (int i = 0; i < keywords.length; i++) {
            if (keywordPositions[i] == -1 && !keywords[i].isOptional) {
                throw new Exception(keywords[i].keywordMeaning + " is missing");
            }
        }

        int[] providedKeywordPositions = new int[keywords.length];
        int providedKeywordCount = 0;
        for (int i = 0; i < keywords.length; i++) {
            if (keywordPositions[i] == -1) {
                continue;
            }
            if (providedKeywordCount == 0) {
                providedKeywordPositions[providedKeywordCount] = keywordPositions[i];
                providedKeywordCount++;
                continue;
            }
            if (keywordPositions[i] < providedKeywordPositions[providedKeywordCount - 1]) {
                throw new Exception("Input format is incorrect. Check the help menu");
            }
            providedKeywordPositions[providedKeywordCount] = keywordPositions[i];
            providedKeywordCount++;
        }


        String[] userInputs = new String[keywords.length];
        int parsedKeywordCount = 0;
        for (int i = 0; i < keywords.length; i++) {
            if (keywordPositions[i] == -1) {
                userInputs[i] = null;
                continue;
            }
            int startIndex = keywordPositions[i] + keywords[i].keywordMarker.length();
            int endIndex = parsedKeywordCount + 1 == providedKeywordCount ?
                    userInput.length() : providedKeywordPositions[parsedKeywordCount + 1];
            if (startIndex >= endIndex) {
                throw new Exception(keywords[i].keywordMeaning + " is missing");
            }
            userInputs[i] = userInput.substring(startIndex, endIndex).trim();
            if (userInputs[i].isBlank()) {
                throw new Exception(keywords[i].keywordMeaning + " cannot be empty");
            }
            parsedKeywordCount++;
        }
        return userInputs;
    }
}
