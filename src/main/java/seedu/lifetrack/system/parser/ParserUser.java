package seedu.lifetrack.system.parser;

import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.user.User;

import static seedu.lifetrack.system.exceptions.ErrorMessages.getInvalidExerciseLevelsNumberMessage;
import static seedu.lifetrack.system.exceptions.ErrorMessages.getInvalidNumberOfSetUpInputs;
import static seedu.lifetrack.system.exceptions.ErrorMessages.getInvalidGoalNumberMessage;

public class ParserUser {
    private static final int LABEL_SIZE = 2;

    public static void parseSetUp(String input, User user) throws InvalidInputException, NumberFormatException {
        int heightIndex = input.indexOf("h/");
        int weightIndex = input.indexOf("w/");
        int ageIndex = input.indexOf("a/");
        int sexIndex = input.indexOf("s/");
        int exerciseLevelsIndex = input.indexOf("e/");
        int goalIndex = input.indexOf("g/");

        if (heightIndex == -1 || weightIndex == -1 || ageIndex == -1 || sexIndex == -1
                || exerciseLevelsIndex == -1 || goalIndex == -1) {
            throw new InvalidInputException(getInvalidNumberOfSetUpInputs());
        }
        checkSetUpInputsCorrectOrder(heightIndex, weightIndex, ageIndex, sexIndex, exerciseLevelsIndex, goalIndex);

        String[] parts = input.split("h/|w/|a/|s/|e/|g/");
        String name = parts[0].trim();
        int height = Integer.parseInt(parts[1].trim());
        int weight = Integer.parseInt(parts[2].trim());
        int age = Integer.parseInt(parts[3].trim());
        String sex = parts[4].trim().toLowerCase();
        String exerciseLevels = parseExerciseLevels(parts[5].trim());
        String goal = parseGoalIndex(parts[6].trim());
        user.setName(name);
        user.setHeight(height);
        user.setWeight(weight);
        user.setAge(age);
        user.setSex(sex);
        user.setExerciseLevels(exerciseLevels);
        user.setGoal(goal);
    }

    private static String parseGoalIndex(String input) throws InvalidInputException {
        try {
            int goalNumber = Integer.parseInt(input);
            if (goalNumber == 1) {
                return "fatloss reckless";
            } else if (goalNumber == 2) {
                return "fatloss aggressive";
            } else if (goalNumber == 3) {
                return "fatloss moderate";
            } else if (goalNumber == 4) {
                return "moderate";
            } else if (goalNumber == 5) {
                return "bulking slow";
            } else if (goalNumber == 6) {
                return "bulking normal";
            } else {
                return "bulking aggressive";
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException(getInvalidGoalNumberMessage());
        }
    }

    private static String parseExerciseLevels(String input) throws InvalidInputException {
        try {
            int levelInNumber = Integer.parseInt(input);
            if (levelInNumber == 1) {
                return "little";
            } else if (levelInNumber == 2) {
                return "light";
            } else if (levelInNumber == 3) {
                return "moderate";
            } else if (levelInNumber == 4) {
                return "heavy";
            } else {
                return "veryheavy";
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException(getInvalidExerciseLevelsNumberMessage());
        }
    }

    private static void checkSetUpInputsCorrectOrder(int heightIndex, int weightIndex, int ageIndex, int sexIndex,
                                                     int exerciseLevelsIndex, int goalIndex)
            throws InvalidInputException {
        if (!(heightIndex < weightIndex && weightIndex < ageIndex && sexIndex < exerciseLevelsIndex
                && exerciseLevelsIndex < goalIndex)) {
            throw new InvalidInputException(getInvalidExerciseLevelsNumberMessage());
        }
    }
}
