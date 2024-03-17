package byteceps.processing;

import byteceps.commands.Parser;
import byteceps.errors.Exceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExerciseManagerTest {
    private Parser parser;
    private ExerciseManager exerciseManager;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setup() {
        parser = new Parser();
        exerciseManager = new ExerciseManager();
    }

    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void execute_addValidExercise_success() {
        String validInput = "exercise /add Push-ups";
        parser.parseInput(validInput);
        assertDoesNotThrow(() -> exerciseManager.execute(parser));
    }

    @Test
    public void execute_addEmptyNameExercise_throwsInvalidInput() {
        String emptyInput = "exercise /add";
        parser.parseInput(emptyInput);
        assertThrows(Exceptions.InvalidInput.class, ()-> exerciseManager.execute(parser));
    }

    @Test
    public void execute_addDuplicateExercise_throwsActivityExists() {
        String validInput = "exercise /add Push-ups";
        parser.parseInput(validInput);
        assertDoesNotThrow(() -> exerciseManager.execute(parser));
        assertThrows(Exceptions.ActivityExistsException.class, () -> exerciseManager.execute(parser));
    }

    @Test
    public void execute_deleteValidExercise_success() {
        String validInput = "exercise /add Push-ups";
        parser.parseInput(validInput);
        assertDoesNotThrow(() -> exerciseManager.execute(parser));

        String deleteInput = "exercise /delete Push-ups";
        parser.parseInput(deleteInput);
        assertDoesNotThrow(() -> exerciseManager.execute(parser));
    }

    @Test
    public void execute_deleteInvalidExercise_throwsActivityDoesNotExist() {
        String invalidInput = "exercise /delete Run";
        parser.parseInput(invalidInput);
        assertThrows(Exceptions.ActivityDoesNotExists.class, () -> exerciseManager.execute(parser));
    }

    @Test
    public void execute_listExercises_success() {
        setUpStreams();

        String validInput1 = "exercise /add Push-ups";
        String validInput2 = "exercise /add Deadlifts";

        parser.parseInput(validInput1);
        assertDoesNotThrow(() -> exerciseManager.execute(parser));
        parser.parseInput(validInput2);
        assertDoesNotThrow(() -> exerciseManager.execute(parser));

        exerciseManager.list();
        String expectedOutput = "[ByteCep]> Added Exercise: Push-ups\n" +
                "[ByteCep]> Added Exercise: Deadlifts\n" +
                "[ByteCep]> Listing Exercises\n" +
                "\t\t\t1. Push-ups\n" +
                "\t\t\t2. Deadlifts\n";

        assertEquals(expectedOutput, outContent.toString());

        restoreStreams();
    }

    @Test
    public void execute_invalidExerciseAction_throwsIllegalState() {
        String invalidInput = "exercise /unknown";
        parser.parseInput(invalidInput);

        assertThrows(IllegalStateException.class, ()->exerciseManager.execute(parser));
    }

    @Test
    public void execute_validExerciseEdit_success() {
        setUpStreams();

        String validInput = "exercise /add Push-ups";
        parser.parseInput(validInput);
        assertDoesNotThrow(() -> exerciseManager.execute(parser));
        exerciseManager.list();

        String editedInput = "exercise /edit Push-ups /to Push Ups";
        parser.parseInput(editedInput);
        assertDoesNotThrow(() -> exerciseManager.execute(parser));
        exerciseManager.list();

        String expectedOutput = "[ByteCep]> Added Exercise: Push-ups\n" +
                "[ByteCep]> Listing Exercises\n" +
                "\t\t\t1. Push-ups\n" +
                "[ByteCep]> Edited Exercise from Push-ups to Push Ups\n" +
                "[ByteCep]> Listing Exercises\n" +
                "\t\t\t1. Push Ups\n";

        assertEquals(expectedOutput, outContent.toString());

        restoreStreams();
    }

    @Test
    public void execute_invalidExerciseEdit_throwsInvalidInput() {
        String invalidInput = "exercise /edit";
        parser.parseInput(invalidInput);
        assertThrows(Exceptions.InvalidInput.class, () -> exerciseManager.execute(parser));

        String invalidInput2 = "exercise /edit non-existent";
        parser.parseInput(invalidInput2);
        assertThrows(Exceptions.InvalidInput.class, () -> exerciseManager.execute(parser));

    }
}
