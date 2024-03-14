package seedu.lifetrack.parser;

import seedu.lifetrack.activity.Activity;
import seedu.lifetrack.calorielist.Entry;
import seedu.lifetrack.calories.Calorie;
import seedu.lifetrack.exceptions.InvalidInputException;

import java.util.Objects;

public class Parser {

    /**
     * Parses a string input to create an Entry object representing calorie intake.
     *
     * This method expects the input string to follow a specific format, where the
     * date, time, activity description, and calorie count are separated by the
     * delimiters 'd/', 't/', 'a/', and 'c/'. The method extracts these components
     * and creates an Entry object containing an Activity and a Calorie object.
     * If any part of the input is missing or empty, an InvalidInputException is thrown.
     *
     * @param input the input string to be parsed, containing date, time, activity,
     *              and calorie count information
     * @return an Entry object representing calorie intake
     * @throws InvalidInputException if the input string is missing components or
     *                              contains empty fields
     */
    public static Entry parseCaloriesIn(String input) throws InvalidInputException {
        //splits string according to d/ , t/ , a/ , c/ keyword
        String[] parts = input.split("d/|t/|a/|c/");
        //parts length less than 5 means that not all split keywords were keyed in
        if (parts.length < 5) {
            throw new InvalidInputException();
        }

        //extracts command, date, time, activity, calories_in portion from input
        String command = parts[0].trim();
        String date = parts[1].trim();
        String time = parts[2].trim();
        return getNewCalorieInEntry(parts, date, time, command);
    }

    private static Entry getNewCalorieInEntry(String[] parts, String date, String time, String command)
            throws InvalidInputException {
        String description = parts[3].trim();
        String strCalories = parts[4].trim();
        //ensures that all inputs are not empty
        if (date.isEmpty() || time.isEmpty() || description.isEmpty() || strCalories.isEmpty()) {
            throw new InvalidInputException();
        }
        int calories = Integer.parseInt(strCalories);

        //create objects for Activity, Calorie
        Activity activityToAdd = new Activity(date, time, description);
        Calorie caloriesConsumed = new Calorie(calories, Objects.equals(command, "calories in"));

        //create Object Entry to be returned
        return new Entry(activityToAdd, caloriesConsumed);
    }
}
