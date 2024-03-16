package data;

// uncomment below line when WeekView is implemented
// import main.WeekView;

import Time.WeekView;

import java.time.LocalDate;
import java.util.List;

public class TaskManagerException extends Exception {

    public static final String NOT_CURRENT_WEEK_MESSAGE = "The date must be within the current week. Please try again.";
    public static final String NO_TASKS_IN_CURRENT_WEEK_MESSAGE = "No tasks to delete on this date.";
    public TaskManagerException (String errorMessage) {
        super(errorMessage);
    }

    public static void checkIfDateInCurrentWeek (LocalDate date, WeekView weekView) throws TaskManagerException {
        if (date.isBefore(weekView.getStartOfWeek()) || date.isAfter(weekView.getStartOfWeek().plusDays(6))) {
            throw new TaskManagerException(NOT_CURRENT_WEEK_MESSAGE);
        }
    }

    public static void checkIfDateHasTasks (List<String> dayTasks) throws TaskManagerException {
        if (dayTasks.isEmpty()) {
            throw new TaskManagerException(NO_TASKS_IN_CURRENT_WEEK_MESSAGE);
        }
    }
}
