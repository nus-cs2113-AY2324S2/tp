package byteceps.activities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ExerciseTest {

    @Test
    public void toString_activityName_returnsActivityName() {
        String exerciseName = "Bench Press";
        Exercise exercise = new Exercise(exerciseName);

        assertEquals(exerciseName, exercise.toString());
    }


    @Test
    public void editExerciseName_validName_setNewName() {
        String initialName = "Push-ups";
        Exercise exercise = new Exercise(initialName);

        String newName = "Pull-ups";
        exercise.editExerciseName(newName);
        assertEquals(newName, exercise.getActivityName());
    }

    @Test
    public void editExerciseName_emptyName_setEmptyName() {
        String initialName = "Squats";
        Exercise exercise = new Exercise(initialName);

        String newName = "";
        exercise.editExerciseName(newName);
        assertEquals(newName, exercise.getActivityName());
    }

    @Test
    public void editExerciseName_nullName_setNull() {
        String initialName = "Deadlifts";
        Exercise exercise = new Exercise(initialName);

        String newName = null;
        exercise.editExerciseName(newName);
        assertNull(exercise.getActivityName());
    }

    @Test
    public void editExerciseName_sameName_noChange() {
        Exercise exercise = new Exercise("Walking");
        exercise.editExerciseName("Walking");
        assertEquals("Walking", exercise.getActivityName());
    }

}
