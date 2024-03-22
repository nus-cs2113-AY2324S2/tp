package time;

import data.Task;
import data.TaskManager;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static ui.UiRenderer.printSeparator;
import static ui.UiRenderer.printWeekHeader;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MonthView extends View {
    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public MonthView(LocalDate startOfMonth, DateTimeFormatter dateFormatter) {
        super(startOfMonth, dateFormatter);
    }

    @Override
    public void printView(TaskManager taskManager) {
        logger.log(Level.INFO, "Printing calendar in month view");
        assert startOfView != null : "Start of Month missing!";
        YearMonth yearMonth = YearMonth.from(startOfView);
        LocalDate firstOfMonth = startOfView.withDayOfMonth(1);
        LocalDate current = firstOfMonth.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.SUNDAY));

        // Create a formatter that only prints the day of the month
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d");

        System.out.println("\nMonth View: " + yearMonth.getMonth() + " " + yearMonth.getYear());
        printWeekHeader();

        while (current.isBefore(firstOfMonth.plusMonths(1))) {
            for (int i = 0; i < 7; i++) {
                if (current.getMonth().equals(yearMonth.getMonth())) {
                    // Use the dayFormatter to print only the day number
                    System.out.printf("| %-10s ", dayFormatter.format(current));
                } else {
                    System.out.print("|            ");
                }
                current = current.plusDays(1);
            }
            System.out.println("|");
            printSeparator();

            // Find the maximum number of tasks for any day in the current week
            LocalDate weekStart = current.minusDays(7);
            int maxTasks = 0;
            for (int i = 0; i < 7; i++) {
                LocalDate date = weekStart.plusDays(i);
                maxTasks = Math.max(maxTasks, taskManager.getTasksForDate(date).size());
            }

            // Print tasks for each day in the current week
            for (int taskIndex = 0; taskIndex < maxTasks; taskIndex++) {
                for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
                    LocalDate date = weekStart.plusDays(dayIndex);
                    List<Task> dayTasks = taskManager.getTasksForDate(date);
                    if (taskIndex < dayTasks.size()) {
                        String task = dayTasks.get(taskIndex).getName();
                        System.out.printf("| %-10.10s ", task);
                    } else {
                        System.out.print("|            ");
                    }
                }
                System.out.println("|");
            }

            // Print the separator only if there were tasks in the week
            if (maxTasks > 0) {
                printSeparator();
            }
        }
    }

    @Override
    public void next() {
        startOfView = startOfView.plusMonths(1);
    }

    @Override
    public void previous() {
        startOfView = startOfView.minusMonths(1);
    }

    public LocalDate getStartOfMonth() {
        return startOfView.withDayOfMonth(1);
    }
}
