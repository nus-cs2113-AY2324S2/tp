package byteceps.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import byteceps.exercises.ExerciseManager;


class ExerciseCommandTest {

    private ExerciseManager exerciseManager;

    @BeforeEach
    public void setUp() {
        exerciseManager = ExerciseManager.getInstance();
        exerciseManager.addExercise("Push-ups");
        exerciseManager.addExercise("Sit-ups");
    }

    @Test
    public void addExercise_validName_success() {
        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("add", "Push-ups"), null);
        CommandResult result = exerciseCommand.execute();
        assertEquals("Exercise 'Push-ups' added successfully.", result.feedbackToUser);
    }

    @Test
    public void addExercise_emptyName_failure() {
        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("add", ""), null);
        CommandResult result = exerciseCommand.execute();
        assertEquals("Exercise name cannot be empty.", result.feedbackToUser);
    }

    @Test
    public void deleteExercise_validName_success() {
        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("delete", "Push-ups"), null);
        CommandResult result = exerciseCommand.execute();
        assertEquals("Exercise 'Push-ups' deleted successfully.", result.feedbackToUser);
    }

    @Test
    public void deleteExercise_emptyName_failure() {
        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("delete", ""), null);
        CommandResult result = exerciseCommand.execute();
        assertEquals("Exercise name cannot be empty.", result.feedbackToUser);
    }

    @Test
    public void deleteExercise_nonExistingExercise_failure() {
        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("delete", "Running"), null);
        CommandResult result = exerciseCommand.execute();
        assertEquals("Exercise 'Running' does not exist.", result.feedbackToUser);
    }


    @Test
    public void listExercises_noExercisesInList_shouldReturnNoExercisesFoundMessage() {
        exerciseManager.deleteExercise("Push-ups");
        exerciseManager.deleteExercise("Sit-ups");
        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("list", ""), null);
        CommandResult result = exerciseCommand.execute();
        assertEquals("No exercises found.", result.feedbackToUser);
    }

    @Test
    public void listExercises_oneExerciseInList_shouldReturnListWithOneExercise() {
        exerciseManager.deleteExercise("Sit-ups");
        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("list", ""), null);
        CommandResult result = exerciseCommand.execute();
        assertEquals("List of exercises:\nPush-ups\n", result.feedbackToUser);
    }

    @Test
    public void listExercises_multipleExercisesInList_shouldReturnListWithMultipleExercises() {
        exerciseManager.addExercise("Squats");
        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("list", ""), null);
        CommandResult result = exerciseCommand.execute();
        assertEquals("List of exercises:\nPush-ups\nSit-ups\nSquats\n", result.feedbackToUser);
    }


}
