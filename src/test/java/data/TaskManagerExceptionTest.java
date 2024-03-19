package data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

import static data.TaskManagerException.checkIfDateInCurrentMonth;

public class TaskManagerExceptionTest {

    @Test
    public void checkIfDateInCurrentMonth_pastDateGiven_exceptionThrown () {
        TaskManagerException thrown = Assertions.assertThrows(TaskManagerException.class, () -> {
            checkIfDateInCurrentMonth(LocalDate.parse("1111-11-11"));
        }, "TaskManagerException was expected");

        Assertions.assertEquals("The date must be within the current month. " + "Please try again.",
                thrown.getMessage());
    }

    @Test
    public void checkIfDateInCurrentMonth_futureDateGiven_exceptionThrown () {
        TaskManagerException thrown = Assertions.assertThrows(TaskManagerException.class, () -> {
            checkIfDateInCurrentMonth(LocalDate.parse("2222-11-11"));
        }, "TaskManagerException was expected");

        Assertions.assertEquals("The date must be within the current month. " + "Please try again.",
                thrown.getMessage());
    }
}
