package data;

import time.WeekView;

import java.time.LocalDate;
import java.util.List;

public class TaskManagerException extends Exception {

    public static final String NOT_CURRENT_WEEK_MESSAGE =
            "The date must be within the current week. Please try again.";
    public static final String NOT_CURRENT_MONTH_MESSAGE =
            "The date must be within the current month. Please try again.";

    /**
     * Constructor for TaskManagerException class
     * Uses constructor from Exception superclass
     *
     * @param errorMessage The error message to be printed to the console
     */

    public TaskManagerException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Method that checks if the date is in the current week shown by the calendar.
     *
     * @param date The date to be checked.
     * @param weekView The current week view shown by the calendar.
     * @throws TaskManagerException if date is not in the current week.
     */

    public static void checkIfDateInCurrentWeek(LocalDate date, WeekView weekView) throws TaskManagerException {
        LocalDate startOfWeek = weekView.getStartOfWeek();
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        if (date.isBefore(startOfWeek) || date.isAfter(endOfWeek)) {
            throw new TaskManagerException(NOT_CURRENT_WEEK_MESSAGE);
        }
    }

    /**
     * Method that checks if the date is in the current month shown by the calendar.
     *
     * @param date The date to be checked.
     * @throws TaskManagerException if date is not in the current month.
     */

    public static void checkIfDateInCurrentMonth(LocalDate date) throws TaskManagerException {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        int providedMonth = date.getMonthValue();
        int providedYear = date.getYear();

        if (currentMonth != providedMonth || currentYear != providedYear) {
            throw new TaskManagerException(NOT_CURRENT_MONTH_MESSAGE);
        }
    }

    /**
     * Method that checks if a specified date has existing tasks.
     *
     * @param dayTasks The List containing the list of tasks for the specified date.
     * @throws TaskManagerException if the specified date has no tasks.
     */

    public static void checkIfDateHasTasks(List<Task> dayTasks) throws TaskManagerException {
        if (dayTasks.isEmpty()) {
            throw new TaskManagerException("No tasks to delete on this date.");
        }
    }
}
