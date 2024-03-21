package seedu.lifetrack.system.parser;

import seedu.lifetrack.liquids.Beverage;
import seedu.lifetrack.liquids.liquidlist.LiquidEntry;
import seedu.lifetrack.system.exceptions.InvalidInputException;

public class ParserLiquid {

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
    public static LiquidEntry parseLiquidInput(String input) throws InvalidInputException {

        // splits string according to b/ and v/ keywords
        String[] parts = input.split("b/|v/");
        // parts length less than 3 means that not all split keywords were keyed in
        if (parts.length < 3) {
            throw new InvalidInputException();
        }

        // extracts beverage name and quantity portion from input
        String beverageName = parts[1].trim();
        String volume = parts[2].trim();

        // ensures that both inputs are not empty
        if (beverageName.isEmpty() || volume.isEmpty()) {
            throw new InvalidInputException();
        }
        return getNewLiquidEntry(volume, beverageName);
    }
    private static LiquidEntry getNewLiquidEntry(String strVolume, String name) throws InvalidInputException {
        int volume = Integer.parseInt(strVolume);

        //create objects for Beverage
        Beverage liquidToAdd = new Beverage(name, volume);

        //create Object Entry to be returned
        return new LiquidEntry(liquidToAdd);
    }
}
