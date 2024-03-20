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

        //check that desc/, c/ and date/ keywords exist in the correct order, else throw exception
        int descriptionIndex = input.indexOf("desc/");
        int caloriesIndex = input.indexOf("c/");
        int dateIndex = input.indexOf("date/");
        int macrosIndex = input.indexOf("m/");
        if (descriptionIndex == -1 || caloriesIndex == -1 || dateIndex == -1 ||
                (!(descriptionIndex < caloriesIndex && caloriesIndex < dateIndex) &&
                macrosIndex != -1 && dateIndex < macrosIndex)) {
            throw new InvalidInputException();
        }

        //extract command, description, calories, date and macronutrients from input
        String[] parts = input.split("desc/|c/|date/|m/");
        String command = parts[0].trim();
        String description = parts[1].trim();
        String strCalories = parts[2].trim();
        String date = parts[3].trim();
        int[] macros;

        //check if optional macronutrients field was provided
        if (parts.length == 5) {
            String macroString = parts[4].trim();
            macros = getMacrosFromString(macroString);
        } else {
            macros = null;
        }

        //check if the description, calories or date fields are empty
        if (description.isEmpty() || strCalories.isEmpty() || date.isEmpty()) {
            throw new InvalidInputException();
        }

        try {
            int calories = Integer.parseInt(strCalories);
            if (command == "calories out") {
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

    private static int[] getMacrosFromString(String macroString) {
        int[] macros = new int[3];
        try {
            String[] macroParts = macroString.split(",");
            int idx = 0;
            for (String macro: macroParts) {
                macros[idx] = Integer.parseInt(macro);
                idx++;
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
