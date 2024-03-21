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
     * @throws InvalidInputException if the input string is missing components or
     *                              contains empty fields
     */
    public static Entry parseCaloriesInput(String input) throws InvalidInputException {

        //check that c/ and date/ keywords exist in the correct order, else throw exception
        int caloriesIndex = input.indexOf("c/");
        int dateIndex = input.indexOf("date/");
        int macrosIndex = input.indexOf("m/");
        if (caloriesIndex == -1 || dateIndex == -1 ||
                (!(caloriesIndex < dateIndex) &&
                        macrosIndex != -1 && dateIndex < macrosIndex) ||
                        dateIndex < caloriesIndex) {
            throw new InvalidInputException("Please ensure that you have keyed in the correct format" +
                    " in the correct order!\n" + "Example input: " +
                    "calories in DESCRIPTION c/INTEGER_CALORIES date/DATE");
        }

        //extract command, description, calories, date and macronutrients from input
        String[] parts = input.split("c/|date/|m/");
        String command = parts[0].substring(0, CALORIES_OUT_PADDING).trim();
        String description;
        if (command.equals("calories out")) {
            description = parts[0].substring(CALORIES_OUT_PADDING, caloriesIndex).trim();
        } else {
            command = parts[0].substring(0, CALORIES_OUT_PADDING - 1).trim();
            description = parts[0].substring(CALORIES_OUT_PADDING - 1, caloriesIndex).trim();
        }
        String strCalories = parts[1].trim();
        String date = parts[2].trim();
        int[] macros;

        if (parts.length == 4) {
            String macroString = parts[3].trim();
            try {
                macros = getMacrosFromString(macroString);
            } catch (InvalidInputException e) {
                throw new InvalidInputException(e.getMessage());
            }
        } else {
            macros = null;
        }

        //check if the description, calories or date fields are empty
        if (description.isEmpty() || strCalories.isEmpty() || date.isEmpty()) {
            throw new InvalidInputException("Please ensure that input parameters are not empty!\n" +
                    "Example input: " + "calories in DESCRIPTION c/INTEGER_CALORIES date/DATE");
        }

        try {
            int calories = Integer.parseInt(strCalories);
            if (command.equals("calories out")) {
                return makeNewOutputEntry(description, calories, date);
            } else if (macros == null) {
                return makeNewInputEntry(description, calories, date);
            } else {
                return makeNewInputEntry(description, calories, date, macros);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please input only numbers into the calories field!");
            return null;
        }
    }

    private static int[] getMacrosFromString(String macroString) throws InvalidInputException {
        int[] macros = new int[3];
        try {
            String[] macroParts = macroString.split(",");
            int idx = 0;
            for (String macro: macroParts) {
                //Exception handling when user puts spaces in m/
                //EG m/123,    , 123
                if (macro.trim().isEmpty()) {
                    throw new InvalidInputException("Invalid input exception: " +
                            "Please ensure that all macronutrients fields are filled up. " +
                            "For example: ....... m/CARBS_INT, PROTEIN_INT, FATS_INT");
                }
                macros[idx] = Integer.parseInt(macro.trim());
                idx++;
            }
//            Exception handling when user does not fill up values for macros
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
