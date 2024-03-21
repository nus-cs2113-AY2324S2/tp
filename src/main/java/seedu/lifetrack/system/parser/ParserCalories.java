package seedu.lifetrack.system.parser;

import seedu.lifetrack.calories.calorielist.Entry;
import seedu.lifetrack.calories.calorielist.InputEntry;
import seedu.lifetrack.calories.calorielist.OutputEntry;
import seedu.lifetrack.calories.Activity;
import seedu.lifetrack.calories.Food;
import seedu.lifetrack.system.exceptions.InvalidInputException;

public class ParserCalories {

    private static final int CARBS_IDX = 0;
    private static final int PROTEINS_IDX = 1;
    private static final int FATS_IDX = 2;
    private static final int CALORIES_OUT_PADDING = 12;

    /**
     * Parses a string input to create an Entry object representing calorie intake.
     *
     * This method expects the input string to follow a specific format, where the
     * description, calorie count, date and macronutrients are separated by the
     * delimiters 'desc/', 'c/', 'date/', and 'm/'. The method extracts these components
     * and creates either an InputEntry or OutputEntry object depending on the user command.
     * If required inputs are missing or empty, an InvalidInputException is thrown.
     *
     * @param input the input string to be parsed, containing date, time, activity,
     *              and calorie count information
     * @return an Entry object representing calorie intake
     * @throws InvalidInputException if the input string is missing components or contains empty fields
     */
    public static Entry parseCaloriesInput(String input) throws InvalidInputException {
        int caloriesIndex = input.indexOf("c/");
        int dateIndex = input.indexOf("date/");
        int macrosIndex = input.indexOf("m/");

        checkKeywordsExist(caloriesIndex, dateIndex);
        assert caloriesIndex != -1 : "The c/ keyword should exist!";
        assert dateIndex != -1 : "The date/ keyword should exist!";

        checkKeywordsCorrectlyOrdered(caloriesIndex, dateIndex, macrosIndex);
        assert caloriesIndex < dateIndex : "The c/ keyword must appear before date/ in the input!";

        //extract command, description, calories, date from input
        String[] parts = input.split("c/|date/|m/");
        String command = parts[0].substring(0, CALORIES_OUT_PADDING).trim();
        String description = getDescriptionFromInput(input, command, caloriesIndex);
        String strCalories = parts[1].trim();
        String date = parts[2].trim();
        
        checkInputsAreNonEmpty(description, strCalories, date);
        assert description != "" : "The description field should be a non-empty string!";
        assert strCalories != "" : "The calories field should be a non-empty string!";
        assert date != "" : "The date field should be a non-empty string!";

        //extract macronutrients if user provided it in their input, otherwise initialise it as null
        int[] macros = null;
        if (macrosIndex != -1) {
            if (command.equals("calories out")) {
                throw new InvalidInputException("Invalid input exception: Calorie output entry cannot have macros");
            }
            String macroString = parts[3].trim();
            try {
                macros = getMacrosFromInput(macroString);
            } catch (InvalidInputException e) {
                throw new InvalidInputException(e.getMessage());
            }
        }

        //convert calories from string to integer
        int calories = getIntegerCaloriesFromInput(strCalories);
        checkCaloriesIsPositiveInteger(calories);
        assert calories > 0 : "Calories value must be a positive integer!";

        if (command.equals("calories out")) {
            return makeNewOutputEntry(description, calories, date);
        } else if (macros == null) {
            return makeNewInputEntry(description, calories, date);
        } else {
            return makeNewInputEntry(description, calories, date, macros);
        }
    }

    private static int getIntegerCaloriesFromInput(String strCalories) {
        int calories = 0;
        try {
            calories = Integer.parseInt(strCalories);
        } catch (NumberFormatException e) {
            System.out.println("Please input only positive integers into the calories field!");
        }
        return calories;
    }

    private static String getDescriptionFromInput(String inputString, String command, int caloriesIndex) {
        String description;
        if (command.equals("calories out")) {
            description = inputString.substring(CALORIES_OUT_PADDING, caloriesIndex).trim();
        } else {
            command = inputString.substring(0, CALORIES_OUT_PADDING - 1).trim();
            description = inputString.substring(CALORIES_OUT_PADDING - 1, caloriesIndex).trim();
        }
        return description;
    }

    private static int[] getMacrosFromInput(String macroString) throws InvalidInputException {
        int[] macros = new int[3];
        try {
            String[] macroParts = macroString.split(",");
            int idx = 0;
            for (String macro: macroParts) {
                //throw exception if user inputs whitespace in the macros field i.e. m/123, ,123
                if (macro.trim().isEmpty()) {
                    throw new InvalidInputException("Invalid input exception: " +
                            "Please ensure that all macronutrients fields are filled up. " +
                            "For example: ....... m/CARBS_INT, PROTEIN_INT, FATS_INT");
                }
                macros[idx] = Integer.parseInt(macro.trim());
                idx++;
            }
            //throw exception if there are missing values in the macros field
            if (idx != 3) {
                throw new InvalidInputException("Invalid input exception: " +
                        "Please ensure that all macronutrients fields are filled up. " +
                        "For example: ....... m/CARBS_INT, PROTEIN_INT, FATS_INT");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please input only numbers into the macronutrients field!");
        }
        return macros;
    }

    private static void checkCaloriesIsPositiveInteger(int calories) throws InvalidInputException {
        if (calories <= 0) {
            throw new InvalidInputException("Please input only positive integers into the calories field!");
        }
    }

    private static void checkInputsAreNonEmpty(String description, String strCalories, String date)
            throws InvalidInputException {
        //check if the description, calories or date fields are empty
        if (description.isEmpty() || strCalories.isEmpty() || date.isEmpty()) {
            throw new InvalidInputException("Please ensure that input parameters are not empty!\n" +
                    "Example input: " + "calories in DESCRIPTION c/INTEGER_CALORIES date/DATE");
        }
    }

    private static void checkKeywordsExist(int caloriesIndex, int dateIndex) throws InvalidInputException {
        //check that c/ and date/ keywords exist in the input, else throw exception
        if (caloriesIndex == -1 || dateIndex == -1) {
            throw new InvalidInputException("Invalid input exception: Please ensure that you have keyed in " +
                    "the correct format in the correct order! Example input: " +
                    "calories in DESCRIPTION c/INTEGER_CALORIES date/DATE m/MACROS");
        }
    }

    private static void checkKeywordsCorrectlyOrdered(int caloriesIndex, int dateIndex, int macrosIndex)
            throws InvalidInputException {        
        if ((macrosIndex != -1 && !(caloriesIndex < dateIndex && dateIndex < macrosIndex)) ||
                (macrosIndex == -1 && !(caloriesIndex < dateIndex))) {
            throw new InvalidInputException("Invalid input exception: Please ensure that you have keyed in " +
                    "the correct format in the correct order! Example input: " +
                    "calories in DESCRIPTION c/INTEGER_CALORIES date/DATE m/MACROS");
        }
    }

    private static Entry makeNewOutputEntry(String description, int calories, String date) {
        Activity newActivity = new Activity();

        return new OutputEntry(description, calories, date, newActivity);
    }

    private static Entry makeNewInputEntry(String description, int calories, String date) {

        return new InputEntry(description, calories, date);
    }

    private static Entry makeNewInputEntry(String description, int calories, String date, int[] foodMacros) {

        Food newFood = new Food(foodMacros[CARBS_IDX], foodMacros[PROTEINS_IDX], foodMacros[FATS_IDX]);

        return new InputEntry(description, calories, date, newFood);
    }
}
