package byteceps.activities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkoutTest {

    @Test
    public void addExercise_addSingleExerciseToWorkout_success() {
        Workout workout = new Workout("Push Day");
        Exercise exercise = new Exercise("Bench Press");

        workout.addExercise(exercise);

        ArrayList<Exercise> expectedList = new ArrayList<>();
        expectedList.add(exercise);
        assertEquals(expectedList, workout.getWorkoutList());
    }

    @Test
    public void addExercise_addMultipleExercisesToWorkout_success() {
        Workout workout = new Workout("Push Day");

        Exercise exercise1 = new Exercise("Bench Press");
        Exercise exercise2 = new Exercise("Overhead Press");
        Exercise exercise3 = new Exercise("Chest Fly");


        workout.addExercise(exercise1);
        workout.addExercise(exercise2);
        workout.addExercise(exercise3);


        ArrayList<Exercise> expectedList = new ArrayList<>();
        expectedList.add(exercise1);
        expectedList.add(exercise2);
        expectedList.add(exercise3);
        assertEquals(expectedList, workout.getWorkoutList());
    }

    @Test
    public void getWorkoutList_workoutWithNoExercises_returnsEmptyList() {
        Workout workout = new Workout("Push Day");
        assertTrue(workout.getWorkoutList().isEmpty());
    }

    @Test
    public void getWorkoutList_workoutWithExercises_returnsListOfExercises() {
        Workout workout = new Workout("Push Day");
        Exercise exercise1 = new Exercise("Bench Press");
        Exercise exercise2 = new Exercise("Overhead Press");
        workout.addExercise(exercise1);
        workout.addExercise(exercise2);

        ArrayList<Exercise> expectedList = new ArrayList<>();
        expectedList.add(exercise1);
        expectedList.add(exercise2);
        assertEquals(expectedList, workout.getWorkoutList());
    }


}

