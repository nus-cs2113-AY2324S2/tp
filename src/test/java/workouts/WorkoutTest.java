package workouts;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkoutTest {
    @Test
    void parseDate_correctDateInput_returnDate() {
        LocalDate result = Workout.parseDate("08/03/2024");
        LocalDate expected = LocalDate.of(2024, 3, 8);
        assertEquals(expected, result);
    }

    @Test
    void parseDate_incorrectDateInput_throwException () {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStream));
        String invalidDate = "2024-03-08";
        Workout.parseDate(invalidDate);
        String errorMessage = outputStream.toString().trim();

        assertTrue(errorMessage.contains("Error parsing date"));
        System.setErr(System.err);

    }
}