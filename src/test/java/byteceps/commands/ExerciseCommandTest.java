package byteceps.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import byteceps.exercises.ExerciseManager;

import java.util.ArrayList;


class ExerciseCommandTest {

    private ExerciseManager exerciseManager;

    @BeforeEach
    public void setUp() {
        exerciseManager = ExerciseManager.getInstance();
        exerciseManager.deleteAllExercises();
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
    public void listExercises_noExercises_success() {
        exerciseManager.deleteExercise("Push-ups");
        exerciseManager.deleteExercise("Sit-ups");

        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("list", ""), null);
        CommandResult result = exerciseCommand.execute();
        assertEquals("No exercises found.", result.feedbackToUser);
    }


    @Test
    public void listExercises_exercisesExist_success() {
        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("list", ""), null);
        CommandResult result = exerciseCommand.execute();
        assertEquals("List of exercises:\nPush-ups\nSit-ups\n", result.feedbackToUser);
    }

    @Test
    public void editExercise_validName_success() {
        ArrayList<InputArguments> additionalArguments= new ArrayList<>();
        InputArguments newExercise= new InputArguments("to", "Decline push-ups");
        additionalArguments.add(newExercise);

        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("edit", "Push-ups"),
                additionalArguments);
        CommandResult result=exerciseCommand.execute();
        assertEquals("Exercise 'Push-ups' edited successfully to 'Decline push-ups'.", result.feedbackToUser);

    }

    @Test
    public void editExercise_emptyPreviousName_failure() {
        ArrayList<InputArguments> additionalArguments= new ArrayList<>();
        InputArguments newExercise= new InputArguments("to", "Decline push-ups");
        additionalArguments.add(newExercise);

        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("edit", ""),
                additionalArguments);
        CommandResult result=exerciseCommand.execute();
        assertEquals("Previous exercise name cannot be empty.", result.feedbackToUser);

    }

    @Test
    public void editExercise_invalidPreviousName_failure() {
        ArrayList<InputArguments> additionalArguments= new ArrayList<>();
        InputArguments newExercise= new InputArguments("to", "Decline push-ups");
        additionalArguments.add(newExercise);

        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("edit", "Pull-ups"),
                additionalArguments);
        CommandResult result=exerciseCommand.execute();
        assertEquals("Previous exercise 'Pull-ups' does not exist.", result.feedbackToUser);

    }

    @Test
    public void editExercise_emptyNewExercise_failure() {
        ArrayList<InputArguments> additionalArguments= new ArrayList<>();

        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("edit", "Push-ups"),
                additionalArguments);
        CommandResult result=exerciseCommand.execute();
        assertEquals("There must be 1 new exercise.", result.feedbackToUser);

    }

    @Test
    public void editExercise_multipleNewExercise_failure() {
        ArrayList<InputArguments> additionalArguments= new ArrayList<>();
        InputArguments newExerciseOne= new InputArguments("to", "Decline push-ups");
        additionalArguments.add(newExerciseOne);
        InputArguments newExerciseTwo= new InputArguments("to", "Archer push-ups");
        additionalArguments.add(newExerciseTwo);


        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("edit", "Push-ups"),
                additionalArguments);
        CommandResult result=exerciseCommand.execute();
        assertEquals("There must be 1 new exercise.", result.feedbackToUser);

    }

    @Test
    public void editExercise_emptyNewExerciseFlag_failure() {
        ArrayList<InputArguments> additionalArguments= new ArrayList<>();
        InputArguments newExercise= new InputArguments("", "Decline push-ups");
        additionalArguments.add(newExercise);

        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("edit", "Push-ups"),
                additionalArguments);
        CommandResult result=exerciseCommand.execute();
        assertEquals("Enter the correct flag for editing exercise: 'to'.", result.feedbackToUser);

    }

    @Test
    public void editExercise_invalidNewExerciseFlag_failure() {
        ArrayList<InputArguments> additionalArguments= new ArrayList<>();
        InputArguments newExercise= new InputArguments("change", "Decline push-ups");
        additionalArguments.add(newExercise);

        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("edit", "Push-ups"),
                additionalArguments);
        CommandResult result=exerciseCommand.execute();
        assertEquals("Enter the correct flag for editing exercise: 'to'.", result.feedbackToUser);

    }

    @Test
    public void editExercise_emptyNewExerciseName_failure() {
        ArrayList<InputArguments> additionalArguments= new ArrayList<>();
        InputArguments newExercise= new InputArguments("to", "");
        additionalArguments.add(newExercise);

        ExerciseCommand exerciseCommand = new ExerciseCommand(new InputArguments("edit", "Push-ups"),
                additionalArguments);
        CommandResult result=exerciseCommand.execute();
        assertEquals("New exercise name cannot be empty.", result.feedbackToUser);

    }


}
