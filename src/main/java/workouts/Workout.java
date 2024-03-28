package workouts;
import java.time.LocalDate;

import utility.CustomExceptions;
import utility.Parser;
import utility.WorkoutConstant;

/**
 * Represents a Workout object for PulsePilot.
 */
public abstract class Workout {
    protected LocalDate date = null;

    /**
     * Overloaded constructor that uses the optional date parameter from user input.
     *
     * @param stringDate String representing the date of the workout.
     */
    public Workout(String stringDate) {
        this.date = Parser.parseDate(stringDate);
    }

    /**
     * Constructor that builds a new Workout object.
     */
    public Workout() {
    }

    /**
     * Returns the date of the workout.
     *
     * @return LocalDate variable representing the date of the workout.
     */
    public LocalDate getDate() {
        return date;
    }


    /**
     * Method checks if Exercise Type is valid (e.g. run or gym).
     * Returns {@code true} if all parameters are valid.
     * Otherwise, throw {@code CustomExceptions.InvalidInput}  or {@code CustomExceptions.InsufficientInput}
     *
     * @param exerciseType String representing the type of exercise which is either {@code run} or {@code gym}
     * @return {@code true} if all parameters are valid.
     */
    public static boolean checkIfExerciseTypeIsValid(String exerciseType) throws CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        if (exerciseType.isBlank()){
            throw new CustomExceptions.InsufficientInput(WorkoutConstant.BLANK_INPUT_FOR_EXERCISE);
        }

        exerciseType = exerciseType.toLowerCase();

        if (!exerciseType.equals(WorkoutConstant.RUN) && ! exerciseType.equals(WorkoutConstant.GYM)) {
            throw new CustomExceptions.InvalidInput(WorkoutConstant.INVALID_INPUT_FOR_EXERCISE);
        }

        return true;
    }



    /**
     * Retrieves the string representation of a Workout object.
     *
     * @return A formatted string representing a Workout object.
     */
    @Override
    public String toString(){
        return getDate().toString();
    }
}
