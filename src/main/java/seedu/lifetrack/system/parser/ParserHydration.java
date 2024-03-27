package seedu.lifetrack.system.parser;

import seedu.lifetrack.Entry;
import seedu.lifetrack.hydration.hydrationlist.HydrationEntry;
import seedu.lifetrack.system.exceptions.InvalidInputException;

import static seedu.lifetrack.system.exceptions.ErrorMessages.getIncorrectVolumeInputMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getHydrationEmptyDescriptionMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getHydrationIncorrectOrderMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getHydrationMissingKeywordMessage;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.getHydrationNegativeIntegerVolumeMessage;

public class ParserHydration {
    
    private static final int HYDRATION_IN_PADDING = 12;

    /**
     * Parses a string input to create a Liquid object representing liquid intake.
     *
     * This method expects the input string to follow a specific format, where the
     * beverage name and quantity are separated by the delimiters 'b/' and 'v/'.
     * The method extracts these components and creates a Liquid object.
     * If any part of the input is missing or empty, an InvalidInputException is thrown.
     *
     * @param input the input string to be parsed, containing beverage name and quantity
     * @return a Liquid object representing liquid intake
     * @throws InvalidInputException if the input string is missing components or
     *                              contains empty fields
     */
    public static Entry parseHydrationInput(String input) throws InvalidInputException {
        
        int volumeIndex = input.indexOf("v/");
        int dateIndex = input.indexOf("date/");

        checkKeywordsExist(dateIndex, volumeIndex);
        assert volumeIndex != -1 : "The v/ keyword should exist!";
        assert dateIndex != -1 : "The date/ keyword should exist!";

        checkKeywordsCorrectlyOrdered(dateIndex, volumeIndex);
        assert volumeIndex < dateIndex : "The v/ keyword must appear before date/ in the input!";

        String[] parts = input.split("v/|date/");
        String description = getDescriptionFromInput(input, volumeIndex);
        String strVolume = parts[1].trim();
        String date = parts[2].trim();

        checkInputsAreNonEmpty(description, strVolume, date);
        assert description != "" : "The description field should be a non-empty string!";
        assert strVolume != "" : "The volume field should be a non-empty string!";
        assert date != "" : "The date field should be a non-empty string!";

        int volume = getIntegerVolumeFromInput(strVolume);
        checkVolumeIsPositiveInteger(volume);
        assert volume > 0 : "Volume value must be a positive integer!";

        return makeNewInputEntry(description, volume, date);
    }

    private static HydrationEntry makeNewInputEntry(String description, int volume, String date) {

        return new HydrationEntry(description, volume, date);
    }

    private static int getIntegerVolumeFromInput(String strVolume) {
        int volume = 0;
        try {
            volume = Integer.parseInt(strVolume);
        } catch (NumberFormatException e) {
            System.out.println(getIncorrectVolumeInputMessage());
        }
        return volume;
    }

    private static void checkVolumeIsPositiveInteger(int volume) throws InvalidInputException {
        if (volume <= 0) {
            throw new InvalidInputException(getHydrationNegativeIntegerVolumeMessage());
        }
    }

    private static void checkInputsAreNonEmpty(String description, String strVolume, String date)
            throws InvalidInputException {
        //check if the description, calories or date fields are empty
        if (description.isEmpty() || strVolume.isEmpty() || date.isEmpty()) {
            throw new InvalidInputException(getHydrationEmptyDescriptionMessage());
        }
    }

    private static String getDescriptionFromInput(String inputString, int volumeIndex) {
        String description;
        description = inputString.substring(HYDRATION_IN_PADDING, volumeIndex).trim();
        return description;
    }

    private static void checkKeywordsCorrectlyOrdered( int dateIndex, int volumeIndex) throws InvalidInputException {
        if (!(volumeIndex < dateIndex)) {
            throw new InvalidInputException(getHydrationIncorrectOrderMessage());
        }
    }

    private static void checkKeywordsExist(int dateIndex, int volumeIndex) throws InvalidInputException {
        //check that v/ and date/ keywords exist in the input, else throw exception
        if (dateIndex == -1 || volumeIndex == -1) {
            throw new InvalidInputException(getHydrationMissingKeywordMessage());
        }
    }
}
