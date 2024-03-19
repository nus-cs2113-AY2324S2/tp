package data;

import time.WeekView;

import java.time.LocalDate;
import java.util.List;

public class TaskManagerException extends Exception {

    public static final String NOT_CURRENT_WEEK_MESSAGE =
            "The date must be within the current week. Please try again.";
    public static final String NOT_CURRENT_MONTH_MESSAGE =
            "The date must be within the current month. Please try again.";

    public TaskManagerException(String errorMessage) {
        super(errorMessage);
    }

    public static void checkIfDateInCurrentWeek(LocalDate date, WeekView weekView) throws TaskManagerException {
        LocalDate startOfWeek = weekView.getStartOfWeek();
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        if (date.isBefore(startOfWeek) || date.isAfter(endOfWeek)) {
            throw new TaskManagerException(NOT_CURRENT_WEEK_MESSAGE);
        }
    }

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


    public static void checkIfDateHasTasks(List<String> dayTasks) throws TaskManagerException {
        if (dayTasks.isEmpty()) {
            throw new TaskManagerException("No tasks to delete on this date.");
        }
    }
}
